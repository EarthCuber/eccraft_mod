package com.eccraft.client.entity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public final class ControlDeathRenderState extends LivingEntityRenderState {
    public boolean reaper;
    public String action = "idle";
    public float actionSeconds;
}

