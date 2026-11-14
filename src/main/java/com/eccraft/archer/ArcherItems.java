package com.eccraft.archer;
import com.eccraft.Eccraft;
import com.eccraft.staff.StaffAttributes;
import java.util.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public final class ArcherItems {
    public static final EntityType<ArcherArrow> ARROW=Registry.register(BuiltInRegistries.ENTITY_TYPE,Eccraft.id("archer_arrow"),
        EntityType.Builder.<ArcherArrow>of(ArcherArrow::new,MobCategory.MISC).sized(.5F,.5F).clientTrackingRange(4).updateInterval(20)
            .build(ResourceKey.create(Registries.ENTITY_TYPE,Eccraft.id("archer_arrow"))));
    private static final List<Item> ITEMS=new ArrayList<>();
    public static List<Item> all(){return List.copyOf(ITEMS);}
    public static void register(){ArcherBalance.initialize();register(ArcherBalance.get());}
    public static void register(ArcherBalance.Data config){
        for(int i=1;i<=5;i++){
            var tier=config.tiers()[i-1];
            for(boolean bow:new boolean[]{true,false}){
                String name=(bow?"bow_t":"quiver_t")+i;
                var key=ResourceKey.create(Registries.ITEM,Eccraft.id(name));
                var props=new Item.Properties().setId(key).durability(bow?tier.bowDurability():tier.quiverDurability()).enchantable(config.enchantability())
                    .attributes(ItemAttributeModifiers.builder().add(StaffAttributes.RANGED_DAMAGE,
                        new AttributeModifier(Eccraft.id(bow?"bow_ranged_power":"quiver_ranged_power"),bow?tier.bowPower():tier.quiverPower(),AttributeModifier.Operation.ADD_VALUE),
                        bow?EquipmentSlotGroup.MAINHAND:EquipmentSlotGroup.OFFHAND).build());
                Item item=bow?new ArcherBowItem(props,tier.damageMultiplier()):new QuiverItem(props,i);
                ITEMS.add(Registry.register(BuiltInRegistries.ITEM,key,item));
            }
        }
    }
    private ArcherItems(){}
}
