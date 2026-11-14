package com.eccraft.client.quest;

import com.eccraft.quest.QuestPayloads;
import com.google.gson.*;
import java.util.*;
import net.minecraft.ChatFormatting;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.OminousBottleAmplifier;

public final class QuestScreen extends Screen {
    private final JsonObject data;
    private int pageIndex;
    private int questIndex;
    public QuestScreen(String json){this(json,"","");}
    public QuestScreen(String json,String pageId,String questId){
        super(Component.translatable("screen.eccraft.quests"));data=JsonParser.parseString(json).getAsJsonObject();
        pageIndex=findPage(pageId);questIndex=findQuest(questId);
    }
    private JsonArray pages(){return data.getAsJsonArray("pages");}
    private JsonObject page(){return pages().isEmpty()?null:pages().get(Math.min(pageIndex,pages().size()-1)).getAsJsonObject();}
    private JsonObject quest(){JsonObject p=page();if(p==null)return null;JsonArray qs=p.getAsJsonArray("quests");return qs.isEmpty()?null:qs.get(Math.min(questIndex,qs.size()-1)).getAsJsonObject();}
    private int findPage(String id){for(int i=0;i<pages().size();i++)if(id.equals(pages().get(i).getAsJsonObject().get("id").getAsString()))return i;return 0;}
    private int findQuest(String id){JsonObject p=page();if(p!=null){JsonArray qs=p.getAsJsonArray("quests");for(int i=0;i<qs.size();i++)if(id.equals(qs.get(i).getAsJsonObject().get("id").getAsString()))return i;}return 0;}
    public String selectedPageId(){JsonObject p=page();return p==null?"":p.get("id").getAsString();}
    public String selectedQuestId(){JsonObject q=quest();return q==null?"":q.get("id").getAsString();}
    private int tabColumns(){return Math.max(1,(width-40)/104);}
    private int listTop(){return 52+22*((pages().size()-1)/tabColumns());}
    private int visibleRows(){return Math.max(1,Math.min(9,(height-listTop()-65)/23));}
    @Override protected void init(){
        clearWidgets();int x=20;
        for(int i=0;i<pages().size();i++){int index=i;String title=pages().get(i).getAsJsonObject().get("title").getAsString();
            addRenderableWidget(Button.builder(Component.literal(title),b->{pageIndex=index;questIndex=0;init();}).bounds(20+(i%tabColumns())*104,18+(i/tabColumns())*22,100,20).build());}
        JsonObject p=page();if(p==null)return;JsonArray qs=p.getAsJsonArray("quests");
        int rows=visibleRows(),start=(questIndex/rows)*rows;
        for(int i=start;i<qs.size()&&i<start+rows;i++){int index=i;JsonObject q=qs.get(i).getAsJsonObject();
            String state=q.get("state").getAsString();Component label=("complete".equals(state)||"claimed".equals(state))?Component.literal("✓ ").withStyle(ChatFormatting.GREEN).append(q.get("title").getAsString()):Component.literal(q.get("title").getAsString());
            addRenderableWidget(Button.builder(label,b->{questIndex=index;init();})
                    .bounds(20,listTop()+(i-start)*23,160,20).build());}
        if(qs.size()>rows){
            var previous=addRenderableWidget(Button.builder(Component.literal("上一页"),b->{questIndex=Math.max(0,start-rows);init();}).bounds(20,height-45,76,20).build());previous.active=start>0;
            var next=addRenderableWidget(Button.builder(Component.literal("下一页"),b->{questIndex=start+rows;init();}).bounds(104,height-45,76,20).build());next.active=start+rows<qs.size();
        }
        JsonObject q=quest();if(q==null)return;String state=q.get("state").getAsString();String action=switch(state){case "available"->"accept";case "active"->"submit";case "complete"->"claim";default->"";};
        if(!action.isEmpty())addRenderableWidget(Button.builder(Component.translatable("quest.eccraft.action."+action),b->{
            ClientPlayNetworking.send(new QuestPayloads.Request(action,q.get("id").getAsString()));})
                .bounds(width-150,height-45,120,20).build());
    }
    @Override public void extractRenderState(GuiGraphicsExtractor g,int mouseX,int mouseY,float partialTick){
        g.fill(8,8,width-8,height-8,0xCC101018);super.extractRenderState(g,mouseX,mouseY,partialTick);
        g.centeredText(font,title,width/2,7,0xFFFFFFFF);JsonObject q=quest();if(q==null){g.text(font,"没有可显示的任务",205,60,0xFFAAAAAA);return;}
        int x=205,y=listTop()+3;g.text(font,q.get("title").getAsString(),x,y,0xFFFFFFFF);y+=18;
        g.textWithWordWrap(font,Component.literal(q.get("description").getAsString()),x,y,Math.max(100,width-x-25),0xFFCCCCCC);y+=42;
        String state=q.get("state").getAsString();String stateText=switch(state){case "complete"->"已完成，可领取奖励";case "active"->"进行中";case "claimed"->"奖励已领取";case "available"->"可接取";default->"未解锁";};
        g.text(font,"状态: "+stateText,x,y,"complete".equals(state)?0xFF55FF55:0xFFFFFF80);y+=18;
        for(JsonElement e:q.getAsJsonArray("objectives")){JsonObject o=e.getAsJsonObject();
            if("submit_item".equals(o.get("type").getAsString())){g.text(font,"提交物品：",x,y,0xFFFFFFFF);y+=13;drawItem(g,o.get("target").getAsString(),o.get("count").getAsInt(),o.get("count").getAsInt(),-1,x,y,mouseX,mouseY);y+=21;}
            else{String line=o.get("type").getAsString()+"  "+o.get("target").getAsString()+"  "+o.get("now").getAsInt()+"/"+o.get("count").getAsInt();g.text(font,line,x,y,0xFFFFFFFF);y+=14;}}
        g.text(font,"任务奖励：",x,y,0xFFFFD060);y+=13;
        if(q.has("experience")&&q.get("experience").getAsInt()>0){g.text(font,"经验值："+q.get("experience").getAsInt(),x,y,0xFF80FF80);y+=14;}int rewardX=x;
        for(JsonElement e:q.getAsJsonArray("rewards")){JsonObject r=e.getAsJsonObject();String amount=r.get("min").getAsInt()==r.get("max").getAsInt()?r.get("min").getAsString():r.get("min").getAsString()+"-"+r.get("max").getAsString();int cellWidth=Math.max(42,10+font.width(amount)+16);if(rewardX>x&&rewardX+cellWidth>width-25){rewardX=x;y+=28;}drawItem(g,r.get("item").getAsString(),r.get("min").getAsInt(),r.get("max").getAsInt(),r.has("ominous_bottle_amplifier")?r.get("ominous_bottle_amplifier").getAsInt():-1,rewardX,y,mouseX,mouseY);rewardX+=cellWidth;}
        if(page()!=null&&Set.of("daily_pool","special_daily").contains(page().has("type")?page().get("type").getAsString():""))g.text(font,"刷新日期: "+data.get("date").getAsString(),x,height-65,0xFFFFAA55);
    }
    @Override public boolean isPauseScreen(){return false;}
    private void drawItem(GuiGraphicsExtractor g,String id,int min,int max,int ominousBottleAmplifier,int x,int y,int mouseX,int mouseY){
        Identifier key=Identifier.tryParse(id);if(key==null||!BuiltInRegistries.ITEM.containsKey(key))return;
        ItemStack stack=new ItemStack(BuiltInRegistries.ITEM.getValue(key));
        if(ominousBottleAmplifier>=0)stack.set(DataComponents.OMINOUS_BOTTLE_AMPLIFIER,new OminousBottleAmplifier(ominousBottleAmplifier));
        g.item(stack,x,y);
        String count=min==max?Integer.toString(min):min+"-"+max;g.text(font,count,x+10,y+9,0xFFFFFFFF,true);
        if(mouseX>=x&&mouseX<x+18&&mouseY>=y&&mouseY<y+18)g.setTooltipForNextFrame(font,stack,mouseX,mouseY);
    }
}
