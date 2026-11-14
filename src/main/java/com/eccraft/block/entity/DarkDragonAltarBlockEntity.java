package com.eccraft.block.entity;

import com.eccraft.block.DarkDragonAltarBlock;
import com.eccraft.entity.DarkDragonEntity;
import com.eccraft.entity.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class DarkDragonAltarBlockEntity extends BlockEntity {
    public static final int COOLDOWN_TICKS = 36000;
    private long cooldownEndTick;

    public DarkDragonAltarBlockEntity(BlockPos pos, BlockState state) {
        super(com.eccraft.darkdragon.DarkDragonContent.ALTAR_ENTITY, pos, state);
    }

    public boolean trySummon(ServerLevel level, Player player, ItemStack summoningStone) {
        if (cooldownEndTick > level.getGameTime()) return false;

        BlockPos spawnPos = worldPosition.above();
        DarkDragonEntity darkdragon = com.eccraft.darkdragon.DarkDragonContent.ENTITY.create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (darkdragon == null) return false;

        darkdragon.setPos(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5);
        if (player != null) {
            float yaw = player.getYRot() + 180.0F;
            darkdragon.setYRot(yaw);
            darkdragon.setYBodyRot(yaw);
            darkdragon.setYHeadRot(yaw);
        }
        if (!level.noCollision(darkdragon)) return false;

        darkdragon.finalizeSpawn(level, level.getCurrentDifficultyAt(spawnPos),
                EntitySpawnReason.SPAWN_ITEM_USE, null);
        darkdragon.startEntrance(darkdragon.position());
        if (!level.addFreshEntity(darkdragon)) return false;

        if (player == null || !player.hasInfiniteMaterials()) summoningStone.shrink(1);
        cooldownEndTick = level.getGameTime() + COOLDOWN_TICKS;
        setChanged();
        level.setBlock(worldPosition, getBlockState().setValue(DarkDragonAltarBlock.ENABLED, false), Block.UPDATE_ALL);
        return true;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, DarkDragonAltarBlockEntity altar) {
        boolean enabled = altar.cooldownEndTick <= level.getGameTime();
        if (enabled && altar.cooldownEndTick != 0L) {
            altar.cooldownEndTick = 0L;
            altar.setChanged();
        }
        if (state.getValue(DarkDragonAltarBlock.ENABLED) != enabled) {
            level.setBlock(pos, state.setValue(DarkDragonAltarBlock.ENABLED, enabled), Block.UPDATE_ALL);
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putLong("CooldownEndTick", cooldownEndTick);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        cooldownEndTick = input.getLongOr("CooldownEndTick", 0L);
    }
}
