package com.eccraft.item;

import com.eccraft.block.ModBlocks;
import com.eccraft.block.entity.SphinxAltarBlockEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class SphinxSummoningStoneItem extends Item {
    public SphinxSummoningStoneItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!level.getBlockState(context.getClickedPos()).is(com.eccraft.sphinx.SphinxContent.ALTAR)) {
            return InteractionResult.PASS;
        }
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        if (!(level instanceof ServerLevel serverLevel)) return InteractionResult.FAIL;
        if (!(level.getBlockEntity(context.getClickedPos()) instanceof SphinxAltarBlockEntity altar)) {
            return InteractionResult.FAIL;
        }
        return altar.trySummon(serverLevel, context.getPlayer(), context.getItemInHand())
                ? InteractionResult.SUCCESS_SERVER
                : InteractionResult.FAIL;
    }
}
