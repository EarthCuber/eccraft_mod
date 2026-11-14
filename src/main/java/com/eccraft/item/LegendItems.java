package com.eccraft.item;
import com.eccraft.Eccraft;
import java.util.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
/** Display items; weapon mechanics can be assigned separately in future updates. */
public final class LegendItems {
 private static final List<Item> ITEMS=new ArrayList<>();
 public static List<Item> all(){return List.copyOf(ITEMS);}
 public static void register(){
  for(String id:List.of("item_legend_book","item_legend_book_2","item_legend_book_3","item_legend_book_4","item_legend_book_5","item_legend_bow","item_legend_bow_2","item_legend_bow_3","item_legend_bow_4","item_legend_bow_5","item_legend_core","item_legend_quiver","item_legend_quiver_2","item_legend_quiver_3","item_legend_quiver_4","item_legend_quiver_5","item_legend_shield","item_legend_shield_2","item_legend_shield_3","item_legend_shield_4","item_legend_shield_5","item_legend_staff","item_legend_staff_2","item_legend_staff_3","item_legend_staff_4","item_legend_staff_5","item_legend_sword","item_legend_sword_2","item_legend_sword_3","item_legend_sword_4","item_legend_sword_5")){
   var key=ResourceKey.create(Registries.ITEM,Eccraft.id(id));
   ITEMS.add(Registry.register(BuiltInRegistries.ITEM,key,new Item(new Item.Properties().setId(key).component(net.minecraft.core.component.DataComponents.LORE,com.eccraft.item.ItemDescriptions.lore(id)))));
  }
 }
 private LegendItems(){}
}
