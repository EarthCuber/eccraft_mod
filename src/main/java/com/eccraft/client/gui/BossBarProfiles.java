package com.eccraft.client.gui;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

/** Shared boss-bar style registry. Each boss only supplies its translation key and avatar. */
public final class BossBarProfiles {
    private static final Map<String, Identifier> AVATARS = new LinkedHashMap<>();
    private static final Map<Identifier, Integer> AVATAR_SIZES = new LinkedHashMap<>();

    static {
        register("entity.eccraft.techtain_ark",texture("techtain_ark_avatar.png"));
        AVATAR_SIZES.put(texture("techtain_ark_avatar.png"),16);
        register("entity.eccraft.anubis", texture("anubis_avatar.png"));
        register("entity.eccraft.icewitch", texture("icewitch_avatar.png"));
        register("entity.eccraft.banshee", texture("banshee_avatar.png"));
        register("entity.eccraft.darkdragon", texture("darkdragon_avatar.png"));
        AVATAR_SIZES.put(texture("darkdragon_avatar.png"), 100);
        AVATAR_SIZES.put(texture("banshee_avatar.png"), 108);
        register("entity.eccraft.samurai", texture("samurai_avatar.png"));
        register("entity.eccraft.sphinx", texture("sphinx_avatar.png"));
        register("entity.eccraft.firelord", texture("firelord_avatar.png"));
        AVATAR_SIZES.put(texture("firelord_avatar.png"), 108);
        register("entity.eccraft.nightgod", texture("nightgod_avatar.png"));
        AVATAR_SIZES.put(texture("nightgod_avatar.png"), 108);
        register("entity.eccraft.castlecore", texture("castlecore_avatar.png"));
        AVATAR_SIZES.put(texture("castlecore_avatar.png"), 100);
        register("entity.eccraft.obsidianguard", texture("obsidianguard_avatar.png"));
        AVATAR_SIZES.put(texture("obsidianguard_avatar.png"), 100);
        AVATAR_SIZES.put(texture("sphinx_avatar.png"), 108);
        AVATAR_SIZES.put(texture("samurai_avatar.png"), 100);
        register("entity.eccraft.junglelord", texture("junglelord_avatar.png"));
        AVATAR_SIZES.put(texture("junglelord_avatar.png"), 108);
        AVATAR_SIZES.put(texture("icewitch_avatar.png"), 108);
    }

    private BossBarProfiles() {
    }

    public static void register(String bossTranslationKey, Identifier avatarTexture) {
        AVATARS.put(bossTranslationKey, avatarTexture);
    }

    public static Identifier avatarFor(Component bossName) {
        if (bossName.getContents() instanceof TranslatableContents contents) {
            return AVATARS.get(contents.getKey());
        }
        return null;
    }

    public static int avatarSize(Identifier texture) {
        return AVATAR_SIZES.getOrDefault(texture, 90);
    }

    private static Identifier texture(String file) {
        return Identifier.fromNamespaceAndPath("eccraft", "textures/gui/boss_bar/" + file);
    }
}
