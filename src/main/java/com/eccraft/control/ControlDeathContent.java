package com.eccraft.control;
import com.eccraft.Eccraft;
import com.eccraft.entity.ControlDeathEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.event.player.*;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import java.util.*;
public final class ControlDeathContent {
 public static final EntityType<ControlDeathEntity> ENTITY=entity();
 public static final Item CONTROLLER=item("death_conctrler",true),BOX_T3=item("item_t3_box",false),BOX_T4=item("item_t4_box",false);
 private static EntityType<ControlDeathEntity> entity(){var key=ResourceKey.create(Registries.ENTITY_TYPE,Eccraft.id("death_conctrl"));return Registry.register(BuiltInRegistries.ENTITY_TYPE,key,EntityType.Builder.of(ControlDeathEntity::new,MobCategory.CREATURE).sized(1F,2.8F).clientTrackingRange(12).updateInterval(1).build(key));}
 private static Item item(String id,boolean controller){var key=ResourceKey.create(Registries.ITEM,Eccraft.id(id));var props=new Item.Properties().setId(key).component(net.minecraft.core.component.DataComponents.LORE,com.eccraft.item.ItemDescriptions.lore(id)).stacksTo(controller?1:64);if(controller)props.attributes(ItemAttributeModifiers.builder().add(Attributes.BLOCK_INTERACTION_RANGE,new AttributeModifier(Eccraft.id("death_controller_reach"),32,AttributeModifier.Operation.ADD_VALUE),EquipmentSlotGroup.HAND).build());return Registry.register(BuiltInRegistries.ITEM,key,controller?new com.eccraft.item.DeathControllerItem(props):new Item(props));}
 public static List<Item> items(){return List.of(BOX_T3,BOX_T4,CONTROLLER);}
 public static InteractionResult toggle(Player player,InteractionHand hand){
  if(player.isSpectator()||!player.isAlive())return InteractionResult.FAIL;
  if(player.getCooldowns().isOnCooldown(player.getItemInHand(hand)))return InteractionResult.FAIL;
  if(!(player instanceof ServerPlayer serverPlayer))return InteractionResult.SUCCESS;
  var level=(ServerLevel)player.level();var bindings=ControlDeathBindings.get(level.getServer());var old=bindings.bound(player.getUUID());
  if(old!=null){bindings.clear(player.getUUID(),old);var entity=level.getEntityInAnyDimension(old);if(entity instanceof ControlDeathEntity death&&player.getUUID().equals(death.ownerUuid()))entity.discard();}
  else{var death=new ControlDeathEntity(ENTITY,level);death.bind(player.getUUID());var forward=net.minecraft.world.phys.Vec3.directionFromRotation(0,player.getYRot()).scale(1.5);death.setPos(player.position().add(forward));if(!level.noCollision(death))death.setPos(player.position());bindings.bind(player.getUUID(),death.getUUID());if(!level.addFreshEntity(death)){bindings.clear(player.getUUID(),death.getUUID());return InteractionResult.FAIL;}}
  player.getCooldowns().addCooldown(player.getItemInHand(hand),100);return InteractionResult.SUCCESS_SERVER;
 }
 private static void clear(ServerPlayer player){var level=(ServerLevel)player.level();var store=ControlDeathBindings.get(level.getServer());var id=store.bound(player.getUUID());if(id==null)return;store.clear(player.getUUID(),id);var entity=level.getEntityInAnyDimension(id);if(entity instanceof ControlDeathEntity death&&player.getUUID().equals(death.ownerUuid()))entity.discard();}
 public static void register(){
  FabricDefaultAttributeRegistry.register(ENTITY,ControlDeathEntity.attributes());
  UseBlockCallback.EVENT.register((player,world,hand,hit)->player.getItemInHand(hand).is(CONTROLLER)?toggle(player,hand):InteractionResult.PASS);
  UseEntityCallback.EVENT.register((player,world,hand,entity,hit)->player.getItemInHand(hand).is(CONTROLLER)?toggle(player,hand):InteractionResult.PASS);
  AttackEntityCallback.EVENT.register((player,world,hand,entity,hit)->{
   if(!player.isSpectator()&&player.getItemInHand(hand).is(CONTROLLER)&&world instanceof ServerLevel level){var id=ControlDeathBindings.get(level.getServer()).bound(player.getUUID());var summon=id==null?null:level.getEntityInAnyDimension(id);if(summon instanceof ControlDeathEntity death&&player.getUUID().equals(death.ownerUuid())&&death.level()==world)death.order(entity);}
   return InteractionResult.PASS;
  });
  ServerPlayConnectionEvents.DISCONNECT.register((handler,server)->clear(handler.player));
  ServerLivingEntityEvents.AFTER_DEATH.register((entity,source)->{if(entity instanceof ServerPlayer player)clear(player);});
 }
 private ControlDeathContent(){}
}
