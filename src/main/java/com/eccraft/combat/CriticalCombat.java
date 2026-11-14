package com.eccraft.combat;
import com.eccraft.Eccraft;
import com.eccraft.staff.StaffSystem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.enchantment.Enchantment;
public final class CriticalCombat {
 public static final ResourceKey<Enchantment> KEY=ResourceKey.create(Registries.ENCHANTMENT,Eccraft.id("critical"));
 public static final class Attack {public final Entity owner;public final boolean critical;public boolean vanilla;Attack(Entity owner,boolean c){this.owner=owner;critical=c;}}
 private static final ThreadLocal<Attack> CURRENT=new ThreadLocal<>();
 public static Attack current(){return CURRENT.get();}
 public static Attack begin(Entity owner){var old=CURRENT.get();CURRENT.set(new Attack(owner,owner instanceof LivingEntity living&&roll(living)));return old;}
 public static void restore(Attack previous){if(previous==null)CURRENT.remove();else CURRENT.set(previous);}
 public static double chance(LivingEntity p){return chance(java.util.stream.Stream.of(EquipmentSlot.MAINHAND,EquipmentSlot.OFFHAND,EquipmentSlot.HEAD,EquipmentSlot.CHEST,EquipmentSlot.LEGS,EquipmentSlot.FEET).map(p::getItemBySlot).toList());}
 public static double chance(Iterable<net.minecraft.world.item.ItemStack> items){int levels=0;for(var stack:items)
  for(var e:stack.getEnchantments().entrySet())if(e.getKey().is(KEY))levels+=Math.clamp(e.getIntValue(),0,5);
  return Math.min(.6,levels*.02);}
 public static boolean roll(LivingEntity p){return !p.level().isClientSide()&&p.getRandom().nextDouble()<chance(p);}
 public static boolean eligible(DamageSource source){return !source.is(DamageTypeTags.IS_FIRE)
  &&(source.is(DamageTypes.PLAYER_ATTACK)||source.is(DamageTypeTags.IS_PROJECTILE)||source.is(StaffSystem.MAGIC));}
 public static boolean critical(DamageSource source){
  if(!eligible(source))return false;
  if(source.getDirectEntity() instanceof CriticalCarrier carrier)return carrier.eccraft$isCritical();
  Attack attack=CURRENT.get();return attack!=null&&attack.owner==source.getEntity()?attack.critical:source.getEntity() instanceof ServerPlayer p&&roll(p);
 }
 public static boolean vanillaCritical(DamageSource source){Attack a=CURRENT.get();return a!=null&&a.owner==source.getEntity()&&a.vanilla;}
 private CriticalCombat(){}
}
