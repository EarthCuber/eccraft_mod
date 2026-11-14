package com.eccraft.mixin;
import com.eccraft.recipe.EccraftRecipeBook;
import java.util.Collection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.ServerRecipeBook;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(ServerRecipeBook.class)
public abstract class EccraftRecipePreviewMixin {
    @Inject(method="sendInitialRecipeBook",at=@At("TAIL"))
    private void eccraft$preview(ServerPlayer player,CallbackInfo ci){EccraftRecipeBook.sendPreviews(player);}
    @Inject(method="removeRecipes",at=@At("TAIL"))
    private void eccraft$keepPreview(Collection<RecipeHolder<?>> recipes,ServerPlayer player,CallbackInfoReturnable<Integer> cir){EccraftRecipeBook.sendPreviews(player);}
}
