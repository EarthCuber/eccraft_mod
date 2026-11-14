package com.eccraft.landmark;
import com.google.gson.*;
import com.eccraft.Eccraft;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.*;
import net.fabricmc.fabric.api.client.rendering.v1.hud.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import java.util.Locale;
public final class LandmarkClient implements ClientModInitializer {
 private static final net.minecraft.resources.Identifier HUD_ICON=Eccraft.id("textures/gui/landmark_hud.png");
 private static final net.minecraft.resources.Identifier TRACK_ICON=Eccraft.id("textures/gui/landmark_tracking.png");
 public static KeyMapping KEY;static JsonObject target;static LandmarkScreen current;
 public void onInitializeClient(){
  KEY=KeyMappingHelper.registerKeyMapping(new KeyMapping("key.eccraft.landmarks",InputConstants.Type.KEYSYM,InputConstants.KEY_K,com.eccraft.staff.client.StaffClient.SKILL.getCategory()));
  ClientTickEvents.END_CLIENT_TICK.register(c->{if(KEY!=null)while(KEY.consumeClick())if(c.player!=null&&ClientPlayNetworking.canSend(LandmarkNetwork.Request.TYPE)){if(c.gui.screen() instanceof LandmarkScreen)continue;current=new LandmarkScreen();c.setScreenAndShow(current);c.player.playSound(net.minecraft.sounds.SoundEvents.BOOK_PAGE_TURN,1.0F,1.0F);current.request("list",0);}});
  ClientPlayConnectionEvents.DISCONNECT.register((h,c)->{target=null;current=null;});
  ClientPlayNetworking.registerGlobalReceiver(LandmarkNetwork.Sync.TYPE,(p,c)->{JsonObject data=JsonParser.parseString(p.json()).getAsJsonObject();if(data.get("kind").getAsString().equals("track")){target=data.has("target")?data.getAsJsonObject("target"):null;if(data.has("arrived")&&data.get("arrived").getAsBoolean()&&c.client().player!=null)c.client().player.playSound(net.minecraft.sounds.SoundEvents.PLAYER_LEVELUP,1.0F,1.0F);if(c.client().gui.screen() instanceof LandmarkScreen screen)screen.refreshButtons();}else if(c.client().gui.screen() instanceof LandmarkScreen s)s.update(data);});
  HudElementRegistry.attachElementAfter(VanillaHudElements.HOTBAR,Eccraft.id("landmarks"),LandmarkClient::draw);
 }
 static void send(JsonObject q){ClientPlayNetworking.send(new LandmarkNetwork.Request(q.toString()));}
 private static void draw(GuiGraphicsExtractor g,DeltaTracker dt){Minecraft c=Minecraft.getInstance();if(c.player==null||c.level==null||c.gui.hud.isHidden())return;int w=g.guiWidth(),h=g.guiHeight();
  if(KEY!=null&&!c.player.isSpectator()){int x=Math.clamp(w/2+160,2,w-24),y=h-22;g.blit(HUD_ICON,x,y,x+22,y+22,0F,1F,0F,1F);g.pose().pushMatrix();g.pose().translate(x+11F,y-8F);g.pose().scale(22F/28F,22F/28F);g.centeredText(c.font,"["+KEY.getTranslatedKeyMessage().getString()+"]",0,0,0xFFFFFFFF);g.pose().popMatrix();}
  if(target==null)return;if(!c.level.dimension().identifier().toString().equals(target.get("dimension").getAsString()))return;
  double tx=target.get("x").getAsDouble(),ty=target.get("y").getAsDouble(),tz=target.get("z").getAsDouble();double distance=c.player.position().distanceTo(new net.minecraft.world.phys.Vec3(tx,ty,tz));
  var camera=c.gameRenderer.mainCamera();var pos=camera.position();org.joml.Vector3f v=new org.joml.Vector3f((float)(tx-pos.x),(float)(ty-pos.y),(float)(tz-pos.z));new org.joml.Quaternionf(camera.rotation()).conjugate().transform(v);
  double f=h/(2*Math.tan(Math.toRadians(c.options.fov().get())/2)),depth=-v.z;double sx=v.x,sy=-v.y;boolean front=depth>0.01;double px=w/2.0+sx*f/Math.max(.01,depth),py=h/2.0+sy*f/Math.max(.01,depth);boolean visible=front&&px>=18&&px<w-18&&py>=30&&py<h-35;
  if(!visible){if(Math.abs(sx)+Math.abs(sy)<.001){sx=0;sy=1;}double r=Math.min((w/2.0-22)/Math.max(.001,Math.abs(sx)),(h/2.0-38)/Math.max(.001,Math.abs(sy)));px=w/2.0+sx*r;py=h/2.0+sy*r;}
  int x=(int)px,y=(int)py;String glyph=visible?"◆":Math.abs(sx)>Math.abs(sy)?(sx>0?"▶":"◀"):(sy>0?"▼":"▲");g.centeredText(c.font,String.format(Locale.ROOT,"%.1f m",distance),x,y-21,0xFFFFFFFF);if(visible){g.blit(TRACK_ICON,x-9,y-9,x+9,y+9,0F,1F,0F,1F);}else{g.centeredText(c.font,glyph,x,y,0xFF7DE5E0);}
 }
}


