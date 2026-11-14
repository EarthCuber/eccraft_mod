package com.eccraft.staff.client;

/** The charging hand rotates rigidly; there is no bow stretch or post-charge shake. */
public final class StaffChargePose {
    // Local +Y runs from handle to tip. Looking along +Y, negative rotation is counterclockwise.
    // Clockwise 20 degrees relative to the previously accepted -90-degree orientation.
    public static final float SHAFT_ROLL_DEGREES = -70F;
    // Camera forward is -Z, so a positive offset moves the pitch pivot toward the player.
    public static final float PIVOT_BACK_OFFSET = .30F;
    public static final float GRIP_Z = -.7F;
    public static float progress(int usedTicks, float partialTick, int chargeTicks) {
        return Math.clamp((usedTicks + Math.clamp(partialTick, 0F, 1F)) / Math.max(1, chargeTicks), 0F, 1F);
    }
    // Camera forward is -Z. The model's shaft points along +Y after its GLB transform.
    // Pitch around X tilts the tip forward, then raises it in the depth/vertical plane.
    public static float angle(float progress, boolean leftHand) {
        return -80F + 72F * Math.clamp(progress, 0F, 1F);
    }
    public static float lift(float progress) { return .25F * Math.clamp(progress, 0F, 1F); }
    private StaffChargePose() {}
}
