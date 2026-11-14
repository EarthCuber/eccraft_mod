package com.eccraft.staff;

import com.eccraft.Eccraft;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public final class StaffAttributes {
    public static final Holder<Attribute> RANGED_DAMAGE = register("romate_damage");
    public static final Holder<Attribute> SPELL_POWER = register("spell_power");
    public static final Holder<Attribute> MAGIC_RESISTANCE = register("magic_resistance");
    private static Holder<Attribute> register(String name) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, Eccraft.id(name),
                new RangedAttribute("attribute.eccraft." + name, 0, name.equals("magic_resistance") ? -1 : 0, 1000000).setSyncable(true));
    }
    public static void register() {}
    private StaffAttributes() {}
}
