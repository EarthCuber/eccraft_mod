package com.eccraft.recipe;

import com.eccraft.Eccraft;
import java.util.ArrayList;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.protocol.game.ClientboundRecipeBookAddPacket;
import net.minecraft.world.item.crafting.*;

public final class EccraftRecipeBook {
    public static final RecipeBookCategory CATEGORY=Registry.register(BuiltInRegistries.RECIPE_BOOK_CATEGORY,Eccraft.id("crafting"),new RecipeBookCategory());
    public static void register() {}
    public static boolean includes(RecipeHolder<?> recipe){return recipe.id().identifier().getNamespace().equals("eccraft")&&recipe.value().getType()==RecipeType.CRAFTING&&!recipe.value().isSpecial();}
    public static void sendPreviews(ServerPlayer player){
        var manager=player.level().getServer().getRecipeManager();
        var entries=new ArrayList<ClientboundRecipeBookAddPacket.Entry>();
        for(var recipe:manager.getRecipes())if(includes(recipe)&&!player.getRecipeBook().contains(recipe.id()))
            manager.listDisplaysForRecipe(recipe.id(),display->entries.add(new ClientboundRecipeBookAddPacket.Entry(display,false,false)));
        if(!entries.isEmpty())player.connection.send(new ClientboundRecipeBookAddPacket(entries,false));
    }
    private EccraftRecipeBook() {}
}
