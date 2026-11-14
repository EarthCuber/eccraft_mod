package com.eccraft.armor;

import com.eccraft.Eccraft;
import com.eccraft.staff.StaffAttributes;
import java.util.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;

public final class KnightArmorItems {
    private static final List<KnightArmorItem> ITEMS=new ArrayList<>();
    public static List<KnightArmorItem> all() { return List.copyOf(ITEMS); }
    public static void register() { register(KnightArmorBalance.load()); }
    public static void register(KnightArmorBalance.Data balance) {
        String[] names={"helmet","chestplate","leggings","boots"};
        EquipmentSlot[] slots={EquipmentSlot.HEAD,EquipmentSlot.CHEST,EquipmentSlot.LEGS,EquipmentSlot.FEET};
        for(int tier=1;tier<=5;tier++)for(int part=0;part<4;part++) {
            String name="knight_t"+tier+"_"+names[part];
            var values=balance.tiers()[tier-1].piece(part);
            var key=ResourceKey.create(Registries.ITEM,Eccraft.id(name));
            var group=EquipmentSlotGroup.bySlot(slots[part]);
            var attributes=ItemAttributeModifiers.builder()
                .add(Attributes.KNOCKBACK_RESISTANCE,new AttributeModifier(Eccraft.id(name+"_knockback_resistance"),values.knockbackResistance(),AttributeModifier.Operation.ADD_VALUE),group)
                .add(Attributes.ARMOR_TOUGHNESS,new AttributeModifier(Eccraft.id(name+"_toughness"),values.armorToughness(),AttributeModifier.Operation.ADD_VALUE),group)
                .add(Attributes.ARMOR,new AttributeModifier(Eccraft.id(name+"_armor"),values.armor(),AttributeModifier.Operation.ADD_VALUE),group)
                .add(Attributes.MAX_HEALTH,new AttributeModifier(Eccraft.id(name+"_health"),values.health(),AttributeModifier.Operation.ADD_VALUE),group)
                .add(StaffAttributes.MAGIC_RESISTANCE,new AttributeModifier(Eccraft.id(name+"_magic_resistance"),values.magicResistance(),AttributeModifier.Operation.ADD_VALUE),group).build();
            var item=new KnightArmorItem(new Item.Properties().setId(key).durability(values.durability())
                .component(net.minecraft.core.component.DataComponents.EQUIPPABLE,
                    net.minecraft.world.item.equipment.Equippable.builder(slots[part])
                        .setAsset(ResourceKey.create(net.minecraft.world.item.equipment.EquipmentAssets.ROOT_ID,Eccraft.id("knight_t"+tier))).build())
                .enchantable(10).attributes(attributes),tier,slots[part]);
            Registry.register(BuiltInRegistries.ITEM,key,item); ITEMS.add(item);
        }
    }
    private KnightArmorItems() {}
}
