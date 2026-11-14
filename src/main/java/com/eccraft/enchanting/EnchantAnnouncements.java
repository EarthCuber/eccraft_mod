package com.eccraft.enchanting;

import java.util.List;
import java.util.ArrayList;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public final class EnchantAnnouncements {
 public static boolean qualifies(String id,int level){return switch(id){
  case "eccraft:frost","eccraft:healing","eccraft:shield_wall","eccraft:explosive" -> level==3;
  case "eccraft:critical" -> level==5;
  default -> false;
 };}
 private static MutableComponent red(Component source){
  var copy=source.copy();copy.setStyle(copy.getStyle().withColor(ChatFormatting.RED));
  var siblings=new ArrayList<>(copy.getSiblings());copy.getSiblings().clear();
  for(var child:siblings)copy.append(red(child));return copy;
 }
 public static List<Component> messages(Component player,ItemStack stack){
  var messages=new ArrayList<Component>();
  for(var entry:stack.getEnchantments().entrySet()){
   if(!qualifies(EquipmentEnchanting.id(entry.getKey()),entry.getIntValue()))continue;
   messages.add(Component.translatable("message.eccraft.rare_enchantment",red(player),red(stack.getHoverName()),
     red(Enchantment.getFullname(entry.getKey(),entry.getIntValue()))).withStyle(ChatFormatting.RED));
  }
  return messages;
 }
 /** Called once only after the table's paid ordinary and hidden results have been applied. */
 public static void broadcast(ServerLevel world,Player player,ItemStack stack){
  for(var message:messages(player.getName(),stack))world.getServer().getPlayerList().broadcastSystemMessage(message,false);
 }
 private EnchantAnnouncements(){}
}
