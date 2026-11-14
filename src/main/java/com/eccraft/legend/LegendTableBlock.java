package com.eccraft.legend;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
public final class LegendTableBlock extends BaseEntityBlock {
 public static final MapCodec<LegendTableBlock> CODEC=simpleCodec(LegendTableBlock::new);
 public LegendTableBlock(Properties properties){super(properties);}
 @Override protected MapCodec<? extends BaseEntityBlock> codec(){return CODEC;}
 @Override public BlockEntity newBlockEntity(BlockPos pos,BlockState state){return new LegendTables.TableEntity(pos,state);}
 @Override protected RenderShape getRenderShape(BlockState state){return RenderShape.INVISIBLE;}
 @Override protected net.minecraft.world.phys.shapes.VoxelShape getShape(BlockState state,net.minecraft.world.level.BlockGetter level,BlockPos pos,net.minecraft.world.phys.shapes.CollisionContext context){return Block.box(3.25,0,5,12.75,14.5,11);}
 public String kind(){return BuiltInRegistries.BLOCK.getKey(this).getPath().substring("legend_table_".length());}
 @Override protected InteractionResult useItemOn(ItemStack stack,BlockState state,Level level,BlockPos pos,Player player,InteractionHand hand,BlockHitResult hit){
  if(hand!=InteractionHand.MAIN_HAND||player.isSpectator())return InteractionResult.PASS;
  var result=LegendTables.upgrade(kind(),stack);if(result.isEmpty())return InteractionResult.PASS;
  if(level.isClientSide())return InteractionResult.SUCCESS;
  // Server serializes interactions: a removed table cannot upgrade a second player's item.
  if(level.getBlockState(pos).getBlock()!=this||!level.setBlock(pos,Blocks.AIR.defaultBlockState(),Block.UPDATE_ALL))return InteractionResult.FAIL;
  player.setItemInHand(hand,result);player.getInventory().setChanged();player.inventoryMenu.broadcastChanges();
  return InteractionResult.SUCCESS_SERVER;
 }
}
