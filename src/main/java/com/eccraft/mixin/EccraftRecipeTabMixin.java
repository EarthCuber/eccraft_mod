package com.eccraft.mixin;
import com.eccraft.Eccraft;
import com.eccraft.recipe.EccraftRecipeBook;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.CraftingMenu;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RecipeBookComponent.class)
public abstract class EccraftRecipeTabMixin {
    @Shadow @Final @Mutable private List<RecipeBookComponent.TabInfo> tabInfos;
    @Inject(method="<init>",at=@At("RETURN"))
    private void eccraft$tab(RecipeBookMenu menu,List<RecipeBookComponent.TabInfo> tabs,CallbackInfo ci){
        if(menu instanceof CraftingMenu){
            var result=new ArrayList<>(tabInfos);
            result.add(new RecipeBookComponent.TabInfo(BuiltInRegistries.ITEM.getValue(Eccraft.id("item_synthesis_t2_tear")),EccraftRecipeBook.CATEGORY));
            tabInfos=List.copyOf(result);
        }
    }
}
