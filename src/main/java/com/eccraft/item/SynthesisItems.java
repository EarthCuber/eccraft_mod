package com.eccraft.item;

import com.eccraft.Eccraft;
import java.util.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public final class SynthesisItems {
    private static final List<Item> ITEMS=new ArrayList<>();
    public static List<Item> all() { return List.copyOf(ITEMS); }
    public static void register() {
        for(String suffix:List.of("archer","blackiron","blood","knight","mage","tear")) {
            var key=ResourceKey.create(Registries.ITEM,Eccraft.id("item_synthesis_t2_"+suffix));
            Item item=new Item(new Item.Properties().setId(key).component(net.minecraft.core.component.DataComponents.LORE,com.eccraft.item.ItemDescriptions.lore("item_synthesis_t2_"+suffix)));
            Registry.register(BuiltInRegistries.ITEM,key,item); ITEMS.add(item);
        }
    }
    private SynthesisItems() {}
}
