package com.eccraft.mixin;
import com.eccraft.recipe.EccraftRecipeBook;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.screens.recipebook.SearchRecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SearchRecipeBookCategory.class)
public abstract class EccraftRecipeSearchMixin {
    @Inject(method="includedCategories",at=@At("RETURN"),cancellable=true)
    private void eccraft$include(CallbackInfoReturnable<List<RecipeBookCategory>> cir){
        if((Object)this==SearchRecipeBookCategory.CRAFTING){var result=new ArrayList<>(cir.getReturnValue());result.add(EccraftRecipeBook.CATEGORY);cir.setReturnValue(List.copyOf(result));}
    }
}
