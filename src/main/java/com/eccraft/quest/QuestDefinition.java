package com.eccraft.quest;

import java.util.List;
import net.minecraft.resources.Identifier;

public record QuestDefinition(Identifier id, String type, String page, String title, String description,
                              List<String> prerequisites, List<Objective> objectives, String rewardLootTable,
                              List<DisplayItem> displayRewards, int experience) {
    public QuestDefinition(Identifier id,String type,String page,String title,String description,List<String> prerequisites,List<Objective> objectives,String rewardLootTable,List<DisplayItem> displayRewards){this(id,type,page,title,description,prerequisites,objectives,rewardLootTable,displayRewards,0);}
    public record Objective(String id, String type, String target, int count,
                            String dimension, double x, double y, double z, double radius) {}
    public record DisplayItem(String item, int min, int max, int ominousBottleAmplifier) {}
}
