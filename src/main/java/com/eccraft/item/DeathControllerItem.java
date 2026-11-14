package com.eccraft.item;
import com.eccraft.control.ControlDeathContent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.*;
public final class DeathControllerItem extends Item {
 public DeathControllerItem(Properties properties){super(properties);}
 @Override public InteractionResult use(Level level,Player player,InteractionHand hand){return ControlDeathContent.toggle(player,hand);}
 @Override public InteractionResult useOn(UseOnContext context){return context.getPlayer()==null?InteractionResult.PASS:ControlDeathContent.toggle(context.getPlayer(),context.getHand());}
}
