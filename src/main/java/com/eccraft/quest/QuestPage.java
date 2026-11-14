package com.eccraft.quest;

import java.util.List;
import net.minecraft.resources.Identifier;

public record QuestPage(Identifier id, String title, String icon, int order, String type,
                        String pool, List<String> quests) {}
