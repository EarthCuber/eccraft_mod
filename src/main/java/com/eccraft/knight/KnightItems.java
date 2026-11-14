package com.eccraft.knight;

import com.eccraft.Eccraft;
import com.eccraft.staff.StaffAttributes;
import java.util.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.*;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public final class KnightItems {
    private static final List<Item> ALL=new ArrayList<>();
    public static List<Item> all(){return List.copyOf(ALL);}
    public static void register(){
        KnightBalance.initialize();register(KnightBalance.get());
        // Attribute changes never heal; clamp excess health after equipment removal.
        ServerTickEvents.END_SERVER_TICK.register(server->server.getPlayerList().getPlayers().forEach(p->{
            if(p.getHealth()>p.getMaxHealth())p.setHealth(p.getMaxHealth());
        }));
    }
    public static void register(KnightBalance.Data config){
        for(int i=1;i<=5;i++){
            var tier=config.tiers()[i-1];
            var swordKey=ResourceKey.create(Registries.ITEM,Eccraft.id("sword_t"+i));
            // Vanilla base attack speed is 4; -3 gives a final attack speed of 1.
            var swordAttrs=ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE,new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID,tier.swordDamage()-1,AttributeModifier.Operation.ADD_VALUE),EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED,new AttributeModifier(Item.BASE_ATTACK_SPEED_ID,-3,AttributeModifier.Operation.ADD_VALUE),EquipmentSlotGroup.MAINHAND).build();
            ALL.add(Registry.register(BuiltInRegistries.ITEM,swordKey,new Item(new Item.Properties().setId(swordKey)
                .sword(ToolMaterial.IRON,3,-3).durability(tier.swordDurability()).enchantable(config.enchantability()).attributes(swordAttrs))));
            var shieldKey=ResourceKey.create(Registries.ITEM,Eccraft.id("shield_t"+i));
            var attrs=ItemAttributeModifiers.builder()
                .add(Attributes.KNOCKBACK_RESISTANCE,new AttributeModifier(Eccraft.id("shield_knockback_resistance"),tier.knockbackResistance(),AttributeModifier.Operation.ADD_VALUE),EquipmentSlotGroup.OFFHAND)
                .add(Attributes.MAX_HEALTH,new AttributeModifier(Eccraft.id("shield_health"),tier.health(),AttributeModifier.Operation.ADD_VALUE),EquipmentSlotGroup.OFFHAND)
                .add(Attributes.ARMOR,new AttributeModifier(Eccraft.id("shield_armor"),tier.armor(),AttributeModifier.Operation.ADD_VALUE),EquipmentSlotGroup.OFFHAND)
                .add(StaffAttributes.MAGIC_RESISTANCE,new AttributeModifier(Eccraft.id("shield_magic_resistance"),tier.magicResistance(),AttributeModifier.Operation.ADD_VALUE),EquipmentSlotGroup.OFFHAND).build();
            ALL.add(Registry.register(BuiltInRegistries.ITEM,shieldKey,new KnightShieldItem(new Item.Properties().setId(shieldKey)
                .durability(tier.shieldDurability()).enchantable(config.enchantability()).attributes(attrs))));
        }
    }
    private KnightItems(){}
}
