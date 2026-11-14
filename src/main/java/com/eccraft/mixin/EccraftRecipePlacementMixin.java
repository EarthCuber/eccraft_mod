package com.eccraft.mixin;
import com.eccraft.recipe.EccraftRecipeBook;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.stats.ServerRecipeBook;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class EccraftRecipePlacementMixin {
    @Shadow public ServerPlayer player;
    @Redirect(method="handlePlaceRecipe",at=@At(value="INVOKE",target="Lnet/minecraft/stats/ServerRecipeBook;contains(Lnet/minecraft/resources/ResourceKey;)Z"))
    private boolean eccraft$allowPreviewPlacement(ServerRecipeBook book,ResourceKey<Recipe<?>> id){
        return book.contains(id)||player.level().getServer().getRecipeManager().byKey(id).filter(EccraftRecipeBook::includes).isPresent();
    }
}
