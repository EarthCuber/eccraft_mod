package com.eccraft.legend;
import com.eccraft.Eccraft;
import java.util.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
public final class LegendTables {
 public static final List<String> KINDS=List.of("sword","shield","staff","tome","bow","quiver");
 private static final List<Block> BLOCKS=new ArrayList<>();
 private static final List<Item> ITEMS=new ArrayList<>();
 public static BlockEntityType<TableEntity> TYPE;
 public static List<Item> items(){return List.copyOf(ITEMS);}
 public static String family(String kind){return kind.equals("holy_staff")?"staff":kind;}
 public static ItemStack upgrade(String table,ItemStack input){
  var d=LegendEquipment.definition(input);if(d==null||d.stage()!=2||!family(d.kind()).equals(table))return ItemStack.EMPTY;
  return new ItemStack(BuiltInRegistries.ITEM.getValue(Eccraft.id("legend_"+d.kind()+"_3")));
 }
 public static void register(){
  for(String kind:KINDS){var id=Eccraft.id("legend_table_"+kind);var key=ResourceKey.create(Registries.BLOCK,id);
   var block=Registry.register(BuiltInRegistries.BLOCK,key,new LegendTableBlock(BlockBehaviour.Properties.of().setId(key).strength(3.5F).sound(SoundType.STONE).noOcclusion()));BLOCKS.add(block);
   var itemKey=ResourceKey.create(Registries.ITEM,id);ITEMS.add(Registry.register(BuiltInRegistries.ITEM,itemKey,new BlockItem(block,new Item.Properties().setId(itemKey).component(net.minecraft.core.component.DataComponents.LORE,com.eccraft.item.ItemDescriptions.lore("legend_table_"+kind)))));
  }
  TYPE=Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,Eccraft.id("legend_table"),new BlockEntityType<>(TableEntity::new,Set.copyOf(BLOCKS)));
 }
 public static final class TableEntity extends BlockEntity {
  public TableEntity(BlockPos pos,BlockState state){super(TYPE,pos,state);}
 }
 private LegendTables(){}
}
