package com.eccraft.quest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.*;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public final class QuestPlayerData {
    private static final Gson GSON = new Gson();
    public final Set<String> active = new HashSet<>();
    public final Set<String> completed = new HashSet<>();
    public final Set<String> claimed = new HashSet<>();
    public final Map<String, Integer> progress = new HashMap<>();
    public final List<String> daily = new ArrayList<>();
    public String dailyDate = "";

    public void save(ValueOutput out) {
        out.putString("EccraftQuestData", GSON.toJson(this));
    }
    public void load(ValueInput in) {
        String json = in.getStringOr("EccraftQuestData", "");
        if (json.isBlank()) return;
        try {
            QuestPlayerData loaded = GSON.fromJson(json, QuestPlayerData.class);
            active.addAll(loaded.active); completed.addAll(loaded.completed); claimed.addAll(loaded.claimed);
            progress.putAll(loaded.progress); daily.addAll(loaded.daily); dailyDate = loaded.dailyDate;
        } catch (RuntimeException ignored) {}
    }
    public void copyFrom(QuestPlayerData other) {
        active.clear(); active.addAll(other.active); completed.clear(); completed.addAll(other.completed);
        claimed.clear(); claimed.addAll(other.claimed); progress.clear(); progress.putAll(other.progress);
        daily.clear(); daily.addAll(other.daily); dailyDate = other.dailyDate;
    }
    public int get(String quest, String objective) { return progress.getOrDefault(quest + "#" + objective, 0); }
    public void set(String quest, String objective, int value) { progress.put(quest + "#" + objective, value); }
    public void clearQuest(String quest) { progress.keySet().removeIf(k -> k.startsWith(quest + "#")); }
}
