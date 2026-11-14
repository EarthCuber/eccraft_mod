package com.eccraft.archer;
import com.eccraft.staff.StaffAttributes;
import net.minecraft.server.level.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.*;

public final class ArcherBowItem extends Item {
    public static final int CHARGE_TICKS=20;
    public static final float ARROW_SPEED=3F;
    private final double multiplier;
    public ArcherBowItem(Properties properties,double multiplier){super(properties);this.multiplier=multiplier;}
    public static boolean ready(int ticks,boolean quiver){return quiver&&ticks>=CHARGE_TICKS;}
    public static boolean hasQuiver(LivingEntity user){return user.getOffhandItem().getItem() instanceof QuiverItem;}
    @Override public int getUseDuration(ItemStack stack,LivingEntity user){return Integer.MAX_VALUE;}
    @Override public ItemUseAnimation getUseAnimation(ItemStack stack){return ItemUseAnimation.BOW;}
    @Override public InteractionResult use(Level level,Player player,InteractionHand hand){
        if(hand!=InteractionHand.MAIN_HAND)return InteractionResult.PASS;
        if(!hasQuiver(player)){player.sendOverlayMessage(net.minecraft.network.chat.Component.translatable("message.eccraft.missing_quiver"));return InteractionResult.FAIL;}
        player.startUsingItem(hand);return InteractionResult.CONSUME;
    }
    @Override public void onUseTick(Level level,LivingEntity user,ItemStack stack,int remaining){if(!hasQuiver(user))user.stopUsingItem();}
    @Override public boolean releaseUsing(ItemStack stack,Level level,LivingEntity user,int remaining){
        if(!ready(getUseDuration(stack,user)-remaining,hasQuiver(user)))return false;
        if(!(user instanceof ServerPlayer player)||!(level instanceof ServerLevel world))return false;
        if(player.getMainHandItem()!=stack||!player.isAlive()||player.isSpectator())return false;
        ItemStack quiver=player.getOffhandItem();
        ArcherArrow arrow=new ArcherArrow(ArcherItems.ARROW,world);
        arrow.setOwner(player);arrow.setPos(player.getX(),player.getEyeY()-.1,player.getZ());
        arrow.configure(((QuiverItem)quiver.getItem()).tier(),(float)(player.getAttributeValue(StaffAttributes.RANGED_DAMAGE)*multiplier));
        arrow.setExplosion(com.eccraft.combat.ExplosiveEnchantment.level(stack));
        arrow.shootFromRotation(player,player.getXRot(),player.getYRot(),0,ARROW_SPEED,1F);
        if(!world.addFreshEntity(arrow))return false;
        stack.hurtAndBreak(1,player,EquipmentSlot.MAINHAND);
        quiver.hurtAndBreak(1,player,EquipmentSlot.OFFHAND);
        world.playSound(null,player.getX(),player.getY(),player.getZ(),SoundEvents.ARROW_SHOOT,SoundSource.PLAYERS,1F,1F);
        player.awardStat(net.minecraft.stats.Stats.ITEM_USED.get(this));return true;
    }
}
