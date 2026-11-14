package com.eccraft.firelord;

import com.eccraft.client.gltf.GltfModel;

/** Native clip speed, excluding the source file's initial timestamp offset. */
public final class FirelordAnimations {
    public record Selection(String clip,float sourceTime,boolean looping) {}
    public static Selection select(GltfModel model,String action,float actionSeconds,float ageSeconds){
        String clip=switch(action){case "attack"->"Anim-1_attack_01";case "skill_1"->"Anim-1_attack_02";
            case "skill_2","skill_3","skill_4"->"Anim-1_attack_03";case "death"->"Anim-1_death";case "walk"->"Anim-1_walk";default->"Anim-1_stand";};
        boolean loop=action.equals("idle")||action.equals("rise")||action.equals("land");
        float seconds=loop?ageSeconds:actionSeconds;
        var anim=model.animations.get(clip);
        float start=start(anim),length=anim==null?0:Math.max(0,anim.duration()-start);
        if(!loop&&!action.equals("death")&&seconds>=length){clip="Anim-1_stand";anim=model.animations.get(clip);seconds-=length;loop=true;start=start(anim);length=anim==null?0:Math.max(0,anim.duration()-start);}
        float time=length<=0?0:loop?((seconds%length)+length)%length:Math.clamp(seconds,0,length);
        // Wrap explicitly over the effective span, then sample without wrapping over the source offset.
        return new Selection(clip,start+time,false);
    }
    private static float start(GltfModel.Animation animation){
        if(animation==null)return 0;
        float start=Float.POSITIVE_INFINITY;
        for(var channel:animation.channels())if(channel.times().length>0)start=Math.min(start,channel.times()[0]);
        return Float.isFinite(start)?start:0;
    }
    private FirelordAnimations(){}
}
