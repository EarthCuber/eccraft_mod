package com.eccraft.client.music;

import com.eccraft.mixin.BossMusicOverlayAccessor;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

/** Client-local music selected from server-tracked boss bars. */
public final class BossBattleMusic {
    private static Track current;
    private static UUID selected;
    private static int retryTicks;
    private BossBattleMusic() {}

    public static boolean tick(Minecraft client) {
        if (client.level == null || client.player == null) {
            if (current != null) client.getSoundManager().stop(current);
            current = null;
            selected = null;
            retryTicks = 0;
            return false;
        }
        if (client.isPaused()) return current != null;
        var events = ((BossMusicOverlayAccessor) client.gui.hud.getBossOverlay()).eccraft$musicEvents();
        Identifier wanted = null;
        if (selected != null && events.containsKey(selected)) wanted = music(events.get(selected));
        if (wanted == null) {
            selected = null;
            for (var entry : events.entrySet()) {
                wanted = music(entry.getValue());
                if (wanted != null) { selected = entry.getKey(); break; }
            }
        }
        // Avoid silencing vanilla music when the user has not supplied the OGG yet.
        if (wanted != null && client.getResourceManager().getResource(
                Identifier.fromNamespaceAndPath("eccraft", "sounds/music/" + wanted.getPath().substring(6) + ".ogg")).isEmpty()) wanted = null;
        if (retryTicks > 0) retryTicks--;
        if (current != null && (current.isStopped() || !client.getSoundManager().isActive(current))) {
            current = null;
            retryTicks = 20;
        }
        if (current != null) current.fadingOut = !current.getIdentifier().equals(wanted);
        // Finish the old fade before starting a different track; never stack boss music.
        if (current == null && wanted != null && retryTicks == 0) {
            current = new Track(wanted);
            client.getSoundManager().play(current);
        }
        return current != null;
    }

    private static Identifier music(net.minecraft.world.BossEvent event) {
        if (event.getProgress() <= 0 || !(event.getName().getContents() instanceof TranslatableContents text)) return null;
        return switch (text.getKey()) {
            case "entity.eccraft.anubis" -> Identifier.fromNamespaceAndPath("eccraft", "music.anubis_battle");
            case "entity.eccraft.icewitch" -> Identifier.fromNamespaceAndPath("eccraft", "music.icewitch_battle");
            case "entity.eccraft.junglelord" -> Identifier.fromNamespaceAndPath("eccraft", "music.junglelord_battle");
            case "entity.eccraft.samurai" -> Identifier.fromNamespaceAndPath("eccraft", "music.samurai_battle");
            case "entity.eccraft.banshee" -> Identifier.fromNamespaceAndPath("eccraft", "music.banshee_battle");
            case "entity.eccraft.sphinx" -> Identifier.fromNamespaceAndPath("eccraft", "music.sphinx_battle");
            case "entity.eccraft.obsidianguard" -> Identifier.fromNamespaceAndPath("eccraft", "music.obsidianguard_battle");
            case "entity.eccraft.castlecore" -> Identifier.fromNamespaceAndPath("eccraft", "music.castlecore_battle");
            case "entity.eccraft.nightgod" -> Identifier.fromNamespaceAndPath("eccraft", "music.nightgod_battle");
            case "entity.eccraft.firelord" -> Identifier.fromNamespaceAndPath("eccraft", "music.firelord_battle");
            case "entity.eccraft.techtain_ark" -> Identifier.fromNamespaceAndPath("eccraft", "music.techtain_ark_battle");
            default -> null;
        };
    }

    private static final class Track extends AbstractTickableSoundInstance {
        private boolean fadingOut;
        Track(Identifier id) {
            super(SoundEvent.createVariableRangeEvent(id), SoundSource.MUSIC, RandomSource.create());
            looping = true;
            delay = 0;
            relative = true;
            attenuation = Attenuation.NONE;
            volume = 0.0F;
        }
        @Override public boolean canStartSilent() { return true; }
        @Override public void tick() {
            volume = Math.max(0.0F, Math.min(1.0F, volume + (fadingOut ? -0.025F : 0.025F)));
            if (fadingOut && volume <= 0.0F) stop();
        }
    }
}
