package com.eccraft.enchanting;
import com.eccraft.armor.*;
import com.eccraft.archer.*;
import com.eccraft.knight.KnightShieldItem;
import com.eccraft.staff.*;
import java.util.*;
import java.util.stream.Stream;
import net.minecraft.core.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
public final class EquipmentEnchanting {
 public static String category(ItemStack stack){var item=stack.getItem();
  var legend=com.eccraft.legend.LegendEquipment.definition(stack);if(legend!=null)return legend.kind().equals("holy_staff")?"staff":legend.kind();
  if(item instanceof StaffItem)return "staff";if(item instanceof StaffTomeItem)return "tome";if(item instanceof MageArmorItem)return "mage_armor";
  if(item instanceof ArcherBowItem)return "bow";if(item instanceof QuiverItem)return "quiver";if(item instanceof ArcherArmorItem)return "archer_armor";
  if(item instanceof KnightShieldItem)return "shield";if(item instanceof KnightArmorItem)return "knight_armor";
  var id=BuiltInRegistries.ITEM.getKey(item);if(id.getNamespace().equals("eccraft")&&id.getPath().matches("sword_t[1-5]"))return "sword";return null;
 }
 public static String id(Holder<Enchantment> h){return h.unwrapKey().map(k->k.identifier().toString()).orElse("");}
 public static ItemStack reference(ItemStack stack,String type){
  EquipmentSlot slot=stack.getItem() instanceof MageArmorItem a?a.slot():stack.getItem() instanceof ArcherArmorItem a?a.slot():stack.getItem() instanceof KnightArmorItem a?a.slot():EquipmentSlot.CHEST;
  return switch(type){case "bow"->new ItemStack(Items.BOW);case "sword"->new ItemStack(Items.IRON_SWORD);case "chest"->new ItemStack(Items.IRON_CHESTPLATE);case "armor"->new ItemStack(switch(slot){case HEAD->Items.IRON_HELMET;case LEGS->Items.IRON_LEGGINGS;case FEET->Items.IRON_BOOTS;default->Items.IRON_CHESTPLATE;});default->stack;};
 }
 public static int weight(TableBalance.Profile p,Holder<Enchantment> h){var entry=p.normal().get(id(h));return entry==null?TableBalance.get().extraVanillaWeight():entry.weight();}
 public static int cap(TableBalance.Profile p,Holder<Enchantment> h){var entry=p.normal().get(id(h));return entry==null?h.value().getMaxLevel():Math.min(entry.maxLevel(),h.value().getMaxLevel());}
 public static boolean allowed(TableBalance.Profile p,Holder<Enchantment> h,ItemStack stack,ItemStack reference){String id=id(h);
  if(p.normal().containsKey(id))return true;if(p.rare().containsKey(id))return false;
  // Other custom enchantments never sneak in through the vanilla fallback.
  return id.startsWith("minecraft:")&&(h.value().isPrimaryItem(stack)||h.value().isPrimaryItem(reference));
 }
 public static EnchantmentInstance pick(RandomSource random,List<EnchantmentInstance> entries,TableBalance.Profile profile){int total=0;for(var e:entries)total=Math.addExact(total,weight(profile,e.enchantment()));int n=random.nextInt(total);for(var e:entries){n-=weight(profile,e.enchantment());if(n<0)return e;}throw new IllegalStateException("Empty candidate list");}
 public static List<EnchantmentInstance> select(RandomSource random,ItemStack stack,int cost,Stream<Holder<Enchantment>> pool){
  var profile=TableBalance.get().profiles().get(category(stack));var result=new ArrayList<EnchantmentInstance>();var enchantable=stack.get(DataComponents.ENCHANTABLE);if(enchantable==null)return result;
  // Same strength jitter and repeated-enchantment test as Minecraft 26.2.
  cost+=1+random.nextInt(enchantable.value()/4+1)+random.nextInt(enchantable.value()/4+1);
  float spread=(random.nextFloat()+random.nextFloat()-1)*.15F;cost=Math.max(1,Math.round(cost+cost*spread));
  var reference=reference(stack,profile.vanillaReference());var candidates=new ArrayList<EnchantmentInstance>();
  for(var h:pool.toList())if(allowed(profile,h,stack,reference)){
   for(int rank=cap(profile,h);rank>=h.value().getMinLevel();rank--)if(cost>=h.value().getMinCost(rank)&&cost<=h.value().getMaxCost(rank)){candidates.add(new EnchantmentInstance(h,rank));break;}
  }
  if(candidates.isEmpty())return result;result.add(pickLevel(random,candidates,profile));
  while(random.nextInt(50)<=cost){EnchantmentHelper.filterCompatibleEnchantments(candidates,result.getLast());if(candidates.isEmpty())break;result.add(pickLevel(random,candidates,profile));cost/=2;}
  return result;
 }
 /** Choose the type by weight, then uniformly choose a rank up to its strength limit. */
 public static EnchantmentInstance pickLevel(RandomSource random,List<EnchantmentInstance> candidates,TableBalance.Profile profile){
  var selected=pick(random,candidates,profile);int min=selected.enchantment().value().getMinLevel();
  return new EnchantmentInstance(selected.enchantment(),min+random.nextInt(selected.level()-min+1));
 }
 public static int rank(RandomSource random){double[] weights=TableBalance.get().rankWeights();double n=random.nextDouble()*(weights[0]+weights[1]+weights[2]);for(int i=0;i<3;i++){n-=weights[i];if(n<0)return i+1;}return 3;}
 public static boolean compatible(ItemStack stack,Holder<Enchantment> h,Holder<Enchantment> preview){return !h.equals(preview)&&stack.getEnchantments().keySet().stream().allMatch(existing->existing.equals(h)||Enchantment.areCompatible(existing,h));}
 public static void apply(RegistryAccess access,ItemStack stack,int option,RandomSource random,Holder<Enchantment> preview){
  String category=category(stack);if(option!=2||category==null)return;var profile=TableBalance.get().profiles().get(category);var registry=access.lookupOrThrow(Registries.ENCHANTMENT);
  Set<String> groups=new HashSet<>();
  for(var entry:profile.rare().entrySet()){
   var rule=entry.getValue();String selected=entry.getKey();
   if(!rule.group().isEmpty()){
    if(!groups.add(rule.group()))continue;double draw=random.nextDouble();selected=null;
    for(var alternative:profile.rare().entrySet())if(alternative.getValue().group().equals(rule.group())){draw-=alternative.getValue().chance();if(draw<0){selected=alternative.getKey();break;}}
    if(selected==null)continue;rule=profile.rare().get(selected);
   }else{
    var h=registry.getOrThrow(ResourceKey.create(Registries.ENCHANTMENT,Identifier.parse(selected)));
    if(!compatible(stack,h,preview)||random.nextDouble()>=rule.chance())continue;
   }
   var h=registry.getOrThrow(ResourceKey.create(Registries.ENCHANTMENT,Identifier.parse(selected)));
   if(!compatible(stack,h,preview))continue;int level=rule.level()==0?rank(random):rule.level();
   stack.enchant(h,Math.min(level,h.value().getMaxLevel()));
  }
 }
 private EquipmentEnchanting(){}
}
