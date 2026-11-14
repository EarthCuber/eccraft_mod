package com.eccraft.mixin;
import com.eccraft.recipe.EccraftRecipeBook;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeManager.class)
public abstract class EccraftRecipeCategoryMixin {
    @Inject(method="unpackRecipeInfo",at=@At("RETURN"),cancellable=true)
    private static void eccraft$categorize(CallbackInfoReturnable<List<RecipeManager.ServerDisplayInfo>> cir){
        var result=new ArrayList<RecipeManager.ServerDisplayInfo>();
        for(var info:cir.getReturnValue()){
            var d=info.display();
            result.add(EccraftRecipeBook.includes(info.parent())?new RecipeManager.ServerDisplayInfo(
                new RecipeDisplayEntry(d.id(),d.display(),d.group(),EccraftRecipeBook.CATEGORY,d.craftingRequirements()),info.parent()):info);
        }
        cir.setReturnValue(result);
    }
}
