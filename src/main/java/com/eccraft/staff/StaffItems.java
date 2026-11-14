package com.eccraft.staff;

import com.eccraft.Eccraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

/** Five staves and five offhand tomes, configured before registry freeze. */
public final class StaffItems {
    public static void register() {
        var balance = StaffBalance.get();
        for (int tier = 1; tier <= 5; tier++) {
            String name = "staff_t" + tier;
            var def = balance.staves()[tier - 1];
            ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id(name));
            Registry.register(BuiltInRegistries.ITEM, key, new StaffItem(new Item.Properties().setId(key)
                    .durability(def.durability()).enchantable(def.enchantability()), def.profile()));
            registerTome("tome_t" + tier, balance.tomePower()[tier - 1], balance.tomeDurability()[tier - 1]);
        }
        // Keep the old debug item ID valid for existing worlds and tags.
        registerTome("staff_tome", balance.legacyTomePower(), balance.tomeDurability()[0]);
    }
    private static void registerTome(String name, double power, int durability) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id(name));
        var attributes = ItemAttributeModifiers.builder().add(StaffAttributes.SPELL_POWER,
                new AttributeModifier(Eccraft.id("offhand_tome_power"), power, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.OFFHAND).build();
        Registry.register(BuiltInRegistries.ITEM, key, new StaffTomeItem(new Item.Properties().setId(key).durability(durability).enchantable(10).attributes(attributes)));
    }
    private StaffItems() {}
}
