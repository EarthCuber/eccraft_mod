package com.eccraft.combat.client;
import com.eccraft.Eccraft;
import com.eccraft.combat.CombatNumberPayload;
import com.eccraft.staff.StaffBalance;
import com.google.gson.Gson;
import java.nio.file.*;
import java.util.*;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.*;
import net.fabricmc.fabric.api.client.rendering.v1.hud.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
public final class CombatNumbers {
 public record Settings(Boolean showCombatNumbers){public Settings{if(showCombatNumbers==null)showCombatNumbers=true;}}
 private record Entry(CombatNumberPayload value,int born,double offset){}
 private static boolean enabled=true;private static int clock;private static Object world;
 private static final List<Entry> ENTRIES=new ArrayList<>();
 private static final Identifier[] TEXTURES={Eccraft.id("textures/gui/combat/normal_digits.png"),Eccraft.id("textures/gui/combat/crit_digits.png"),Eccraft.id("textures/gui/combat/normal_heal.png")};
 public static Path path(){return StaffBalance.directory().resolve("client_visuals.json");}
 public static Component label(){return Component.translatable("options.eccraft.combat_numbers",Component.translatable(enabled?"options.on":"options.off"));}
 public static void toggle(){enabled=!enabled;ENTRIES.clear();try{Files.createDirectories(path().getParent());Files.writeString(path(),new Gson().toJson(new Settings(enabled)));}catch(Exception e){org.slf4j.LoggerFactory.getLogger(CombatNumbers.class).error("Cannot save combat number setting",e);}}
 public static void register(){try{if(Files.exists(path())){var settings=new Gson().fromJson(Files.readString(path()),Settings.class);enabled=settings==null||settings.showCombatNumbers();}}catch(Exception e){enabled=true;org.slf4j.LoggerFactory.getLogger(CombatNumbers.class).warn("Invalid client visuals config retained",e);}
  ClientPlayNetworking.registerGlobalReceiver(CombatNumberPayload.TYPE,(p,c)->{if(enabled&&c.client().level!=null&&p.amount()>0){if(world!=c.client().level){ENTRIES.clear();world=c.client().level;}if(ENTRIES.size()>=128)ENTRIES.removeFirst();ENTRIES.add(new Entry(p,clock,(c.client().level.getRandom().nextDouble()-.5)*.35));}});
  ClientPlayConnectionEvents.DISCONNECT.register((h,c)->{ENTRIES.clear();world=null;});
  ClientTickEvents.END_CLIENT_TICK.register(c->{if(world!=c.level){ENTRIES.clear();world=c.level;}if(!c.isPaused())clock++;ENTRIES.removeIf(e->clock-e.born>=20);});
  HudElementRegistry.attachElementAfter(VanillaHudElements.HOTBAR,Eccraft.id("combat_numbers"),CombatNumbers::draw);
 }
 private static void draw(GuiGraphicsExtractor g,DeltaTracker delta){Minecraft c=Minecraft.getInstance();if(!enabled||c.player==null||c.level==null||c.gui.hud.isHidden())return;
  for(Entry e:ENTRIES){var p=e.value;float age=clock-e.born+delta.getGameTimeDeltaPartialTick(false);if(age<0||age>=20)continue;
   Vec3 point=new Vec3(p.x()+e.offset,p.y()+age*.025,p.z());int x,y;
   if(p.self()){x=g.guiWidth()/2+30+(int)(e.offset*25);y=g.guiHeight()/2+25-(int)age;}
   else{var camera=c.gameRenderer.mainCamera();var local=point.subtract(camera.position()).toVector3f().rotate(new org.joml.Quaternionf(camera.rotation()).conjugate());if(local.z>=0)continue;
    Vec3 screen=c.gameRenderer.projectPointToScreen(point);if(!Double.isFinite(screen.x)||!Double.isFinite(screen.y)||Math.abs(screen.x)>1.2||Math.abs(screen.y)>1.2)continue;
    x=(int)((screen.x+1)*g.guiWidth()/2);y=(int)((1-screen.y)*g.guiHeight()/2);
   }
   int style=Math.clamp(p.style(),0,2),sourceWidth=style==1?34:24,sourceHeight=style==1?46:34;
   int height=style==1?19:15,width=Math.round(height*sourceWidth/(float)sourceHeight);
   String digits=Integer.toString(p.amount());x-=digits.length()*width/2;
   int alpha=(int)(255*Math.clamp((20-age)/8,0,1));
   for(int i=0;i<digits.length();i++)g.blit(net.minecraft.client.renderer.RenderPipelines.GUI_TEXTURED,TEXTURES[style],x+i*width,y,(digits.charAt(i)-'0')*sourceWidth,0,width,height,sourceWidth,sourceHeight,sourceWidth*10,sourceHeight,(alpha<<24)|0xFFFFFF);
  }
 }
 private CombatNumbers(){}
}
