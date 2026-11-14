package com.eccraft.block;

import com.eccraft.block.entity.DarkDragonAltarBlockEntity;
import com.eccraft.block.entity.ModBlockEntityTypes;
import com.eccraft.item.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class DarkDragonAltarBlock extends BaseEntityBlock {
    public static final MapCodec<DarkDragonAltarBlock> CODEC = simpleCodec(DarkDragonAltarBlock::new);
    public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;

    public DarkDragonAltarBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(ENABLED, true));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        builder.add(ENABLED);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DarkDragonAltarBlockEntity(pos, state);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                          Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!stack.is(com.eccraft.darkdragon.DarkDragonContent.SUMMONING_STONE)) return InteractionResult.PASS;
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        if (!(level instanceof ServerLevel serverLevel)) return InteractionResult.FAIL;
        if (!(level.getBlockEntity(pos) instanceof DarkDragonAltarBlockEntity altar)) {
            return InteractionResult.FAIL;
        }
        return altar.trySummon(serverLevel, player, stack)
                ? InteractionResult.SUCCESS_SERVER
                : InteractionResult.FAIL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return createTickerHelper(type, com.eccraft.darkdragon.DarkDragonContent.ALTAR_ENTITY, DarkDragonAltarBlockEntity::serverTick);
    }
}
