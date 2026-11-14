package com.eccraft.legend;

import com.eccraft.Eccraft;
import com.eccraft.staff.*;
import com.eccraft.archer.*;
import com.eccraft.knight.*;
import com.google.gson.Gson;
import java.nio.file.*;
import java.util.*;
import net.minecraft.core.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.*;

public final class LegendEquipment {
 public record Definition(String kind,int stage,int durability,int enchantability,double power,double damage,
  double health,double armor,double magicResistance,double damageMultiplier,StaffProfile profile,Map<String,Integer> enchantments, @com.google.gson.annotations.SerializedName(value="knockback_resistance", alternate={"knockbackResistance"}) Double knockbackResistance){
 public Definition(String kind,int stage,int durability,int enchantability,double power,double damage,
  double health,double armor,double magicResistance,double damageMultiplier,StaffProfile profile,Map<String,Integer> enchantments){this(kind,stage,durability,enchantability,power,damage,health,armor,magicResistance,damageMultiplier,profile,enchantments,null);}

  public Definition{
 knockbackResistance=knockbackResistance==null?(kind.equals("shield")?0.16:0):knockbackResistance;
 if(!Double.isFinite(knockbackResistance)||knockbackResistance<0||knockbackResistance>1)throw new IllegalArgumentException("Invalid knockback resistance");
   if(!List.of("sword","shield","staff","holy_staff","tome","bow","quiver").contains(kind)||stage<1||stage>4||durability<1||enchantability<1)throw new IllegalArgumentException("Invalid legend definition");
   for(double v:new double[]{power,damage,health,armor,magicResistance,damageMultiplier})if(!Double.isFinite(v)||v<0)throw new IllegalArgumentException("Invalid legend value");
   if((kind.equals("staff")||kind.equals("holy_staff"))&&profile==null)throw new IllegalArgumentException("Staff profile required");
   enchantments=Map.copyOf(enchantments);for(var e:enchantments.entrySet())if(Identifier.tryParse(e.getKey())==null||e.getValue()<1||e.getValue()>255)throw new IllegalArgumentException("Invalid innate enchantment");
  }
 }
 public record Data(Map<String,Definition> items){public Data{items=Collections.unmodifiableMap(new LinkedHashMap<>(items));
  for(String kind:List.of("sword","shield","staff","holy_staff","tome","bow","quiver"))for(int i=1;i<=4;i++){
   var d=items.get("legend_"+kind+"_"+i);if(d==null||!d.kind().equals(kind)||d.stage()!=i)throw new IllegalArgumentException("Missing legend tier");
  }
 }}
 private static final Map<Item,Definition> DEFINITIONS=new IdentityHashMap<>();
 private static final List<Item> ITEMS=new ArrayList<>();
 public static List<Item> all(){return List.copyOf(ITEMS);}
 public static Definition definition(ItemStack stack){return DEFINITIONS.get(stack.getItem());}
 public static int stage(ItemStack stack){var d=definition(stack);return d==null?0:d.stage();}
 public static Data load(Path path){try{
  if(!Files.exists(path)){Files.createDirectories(path.toAbsolutePath().getParent());try(var in=LegendEquipment.class.getResourceAsStream("/data/eccraft/legend_equipment_defaults.json")){if(in==null)throw new IllegalStateException("Missing legend defaults");Files.copy(in,path);}}
  var data=new Gson().fromJson(Files.readString(path),Data.class);if(data==null)throw new IllegalArgumentException("Empty legend config");return data;
 }catch(Exception e){throw new IllegalStateException("Invalid legend configuration; retained: "+path,e);}}
 public static void register(){register(load(StaffBalance.directory().resolve("legend_equipment.json")));}
 public static void register(Data data){for(var entry:data.items().entrySet()){
  String name=entry.getKey();var d=entry.getValue();var key=ResourceKey.create(Registries.ITEM,Eccraft.id(name));
  var props=new Item.Properties().setId(key).component(net.minecraft.core.component.DataComponents.LORE,com.eccraft.item.ItemDescriptions.lore(name));if(d.kind().equals("sword"))props.sword(ToolMaterial.IRON,3,-3);
  props.durability(d.durability()).enchantable(d.enchantability()).rarity(d.stage()==4?Rarity.EPIC:Rarity.RARE);
  props.delayedComponent(DataComponents.ENCHANTMENTS,provider->{
   var enchants=new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);var lookup=provider.lookupOrThrow(Registries.ENCHANTMENT);
   for(var e:d.enchantments().entrySet())enchants.set(lookup.getOrThrow(ResourceKey.create(Registries.ENCHANTMENT,Identifier.parse(e.getKey()))),e.getValue());
   return enchants.toImmutable();
  });
  var attrs=ItemAttributeModifiers.builder();
  switch(d.kind()){
   case "sword"->{add(attrs,Attributes.ATTACK_DAMAGE,Item.BASE_ATTACK_DAMAGE_ID,d.damage()-1,EquipmentSlotGroup.MAINHAND);add(attrs,Attributes.ATTACK_SPEED,Item.BASE_ATTACK_SPEED_ID,-3,EquipmentSlotGroup.MAINHAND);}
   case "shield"->{add(attrs,Attributes.KNOCKBACK_RESISTANCE,Eccraft.id(name+"_knockback_resistance"),d.knockbackResistance(),EquipmentSlotGroup.OFFHAND);add(attrs,Attributes.MAX_HEALTH,Eccraft.id(name+"_health"),d.health(),EquipmentSlotGroup.OFFHAND);add(attrs,Attributes.ARMOR,Eccraft.id(name+"_armor"),d.armor(),EquipmentSlotGroup.OFFHAND);add(attrs,StaffAttributes.MAGIC_RESISTANCE,Eccraft.id(name+"_resistance"),d.magicResistance(),EquipmentSlotGroup.OFFHAND);}
   case "tome"->add(attrs,StaffAttributes.SPELL_POWER,Eccraft.id("offhand_tome_power"),d.power(),EquipmentSlotGroup.OFFHAND);
   case "bow","quiver"->add(attrs,StaffAttributes.RANGED_DAMAGE,Eccraft.id(name+"_ranged"),d.power(),d.kind().equals("bow")?EquipmentSlotGroup.MAINHAND:EquipmentSlotGroup.OFFHAND);
  }
  props.attributes(attrs.build());
  Item item=switch(d.kind()){
   case "staff","holy_staff"->new StaffItem(props,d.profile());case "tome"->new StaffTomeItem(props);
   case "shield"->new KnightShieldItem(props);case "bow"->new ArcherBowItem(props,d.damageMultiplier());
   case "quiver"->new QuiverItem(props,d.stage()==4?7:6);default->new Item(props);
  };
  Registry.register(BuiltInRegistries.ITEM,key,item);ITEMS.add(item);DEFINITIONS.put(item,d);
 }}
 private static void add(ItemAttributeModifiers.Builder b,Holder<Attribute> attribute,Identifier id,double amount,EquipmentSlotGroup slot){b.add(attribute,new AttributeModifier(id,amount,AttributeModifier.Operation.ADD_VALUE),slot);}
 private LegendEquipment(){}
}

