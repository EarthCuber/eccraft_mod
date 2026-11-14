package com.eccraft.item;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.component.ItemLore;
import java.util.List;
/** Native item lore with a separate, resource-pack-editable translation per item. */
public final class ItemDescriptions {
 public static ItemLore lore(String id){
  String title=(id.startsWith("legend_table_")?"block.eccraft.":"item.eccraft.")+id;
  return new ItemLore(List.of(Component.translatableWithFallback("item.eccraft."+id+".description","%s描述",Component.translatable(title)).withStyle(ChatFormatting.GRAY).withStyle(style->style.withItalic(false))));
 }
 private ItemDescriptions(){}
}
