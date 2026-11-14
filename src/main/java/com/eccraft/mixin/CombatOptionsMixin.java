package com.eccraft.mixin;
import com.eccraft.combat.client.CombatNumbers;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.network.chat.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(OptionsScreen.class)
public abstract class CombatOptionsMixin extends Screen {
 @Unique private Button eccraft$toggle,eccraft$done;
 protected CombatOptionsMixin(Component title){super(title);}
 @Inject(method="init",at=@At("TAIL")) private void eccraft$add(CallbackInfo ci){
  eccraft$done=null;for(var child:children())if(child instanceof Button b&&b.getMessage().equals(CommonComponents.GUI_DONE)){eccraft$done=b;break;}
  eccraft$toggle=addRenderableWidget(Button.builder(CombatNumbers.label(),b->{CombatNumbers.toggle();b.setMessage(CombatNumbers.label());}).bounds(0,0,150,20).build());eccraft$position();
 }
 @Inject(method="repositionElements",at=@At("TAIL")) private void eccraft$resize(CallbackInfo ci){eccraft$position();}
 @Unique private void eccraft$position(){if(eccraft$toggle==null)return;int half=Math.min(150,(width-24)/2);int y=eccraft$done==null?height-27:eccraft$done.getY();eccraft$toggle.setX(width/2-half-2);eccraft$toggle.setY(y);eccraft$toggle.setWidth(half);if(eccraft$done!=null){eccraft$done.setX(width/2+2);eccraft$done.setWidth(half);}}
}
