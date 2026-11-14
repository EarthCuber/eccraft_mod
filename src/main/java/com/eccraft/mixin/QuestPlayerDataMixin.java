package com.eccraft.mixin;

import com.eccraft.quest.QuestDataHolder;
import com.eccraft.quest.QuestPlayerData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class QuestPlayerDataMixin implements QuestDataHolder {
    @Unique private final QuestPlayerData eccraft$questData = new QuestPlayerData();
    @Override public QuestPlayerData eccraft$quests() { return eccraft$questData; }
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void eccraft$saveQuests(ValueOutput output, CallbackInfo ci) { eccraft$questData.save(output); }
    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void eccraft$loadQuests(ValueInput input, CallbackInfo ci) { eccraft$questData.load(input); }
}
