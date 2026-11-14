package com.eccraft.landmark;
import com.google.gson.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import java.util.*;
public final class LandmarkScreen extends Screen {
 private JsonArray rows=new JsonArray();private boolean admin,filters,editing;private int offset,total,tagIndex;private String tag="",creator="",sort="newest";private boolean favorites;private long editId,confirmDelete;
 private EditBox name,description,creatorBox;private String draftName="",draftDescription="";
 public LandmarkScreen(){super(Component.literal("地标"));}
 void refreshButtons(){init();}
 void update(JsonObject data){rows=data.getAsJsonArray("rows");admin=data.get("admin").getAsBoolean();offset=data.get("offset").getAsInt();total=data.get("total").getAsInt();editing=false;confirmDelete=0;init();}
 void request(String op,long id){JsonObject q=query();q.addProperty("op",op);q.addProperty("id",id);LandmarkClient.send(q);}
 private JsonObject query(){JsonObject q=new JsonObject();q.addProperty("offset",offset);q.addProperty("filterTag",tag);q.addProperty("creator",creator);q.addProperty("sort",sort);q.addProperty("onlyFavorites",favorites);return q;}
 private void button(String label,int x,int y,int w,Runnable r){addRenderableWidget(Button.builder(Component.literal(label),b->r.run()).bounds(x,y,w,20).build());}
 private EditBox box(String value,int x,int y,int w,int max){EditBox b=new EditBox(font,x,y,w,20,Component.literal("输入"));b.setMaxLength(max);b.setValue(value);addRenderableWidget(b);return b;}
 private int top(){return filters?113:65;}
 private int capacity(){return Math.max(1,(height-top()-34)/30);}
 @Override protected void init(){clearWidgets();if(editing){form();return;}button("上传地标",12,28,80,()->{editing=true;editId=0;draftName="";draftDescription="";tagIndex=0;init();});button(filters?"收起筛选":"筛选 ▾",98,28,85,()->{filters=!filters;init();});button("刷新",189,28,55,()->request("list",0));
  if(filters){button("排序："+(sort.equals("newest")?"最新":sort.equals("oldest")?"最早":"点赞"),12,55,100,()->{sort=sort.equals("newest")?"oldest":sort.equals("oldest")?"likes":"newest";offset=0;request("list",0);});button("标签："+(tag.isEmpty()?"全部":tag),118,55,100,()->{int i=LandmarkSystem.TAGS.indexOf(tag)+1;tag=i>=LandmarkSystem.TAGS.size()?"":LandmarkSystem.TAGS.get(i);offset=0;request("list",0);});button(favorites?"仅收藏：是":"仅收藏：否",224,55,100,()->{favorites=!favorites;offset=0;request("list",0);});creatorBox=box(creator,12,81,150,36);button("筛选创建者",168,81,90,()->{creator=creatorBox.getValue().strip();offset=0;request("list",0);});}
  for(int i=0;i<Math.min(capacity(),rows.size());i++){JsonObject e=rows.get(i).getAsJsonObject();int y=top()+i*30;long id=e.get("id").getAsLong();int right=width-12;
   button(e.get("liked").getAsBoolean()?"取消赞":"赞 "+e.get("likes").getAsInt(),right-240,y,55,()->request("like",id));button(e.get("favorite").getAsBoolean()?"已收藏":"收藏",right-181,y,55,()->request("favorite",id));button(LandmarkClient.target!=null&&LandmarkClient.target.get("id").getAsLong()==id?"取消追踪":"追踪",right-122,y,65,()->request("track",id));
   if(admin)button(confirmDelete==id?"确认删":"删除",right-53,y,53,()->{if(confirmDelete==id)request("delete",id);else{confirmDelete=id;init();}});
   if(e.get("mine").getAsBoolean())button("编辑",right-289,y,45,()->{editing=true;editId=id;draftName=e.get("name").getAsString();draftDescription=e.get("description").getAsString();tagIndex=LandmarkSystem.TAGS.indexOf(e.get("tag").getAsString());init();});
  }
  button("上一段",12,height-25,65,()->{offset=Math.max(0,offset-capacity());request("list",0);});button("下一段",83,height-25,65,()->{if(offset+capacity()<total){offset+=capacity();request("list",0);}});
 }
 private void form(){name=box(draftName,30,65,Math.max(150,width-60),48);description=box(draftDescription,30,110,Math.max(150,width-60),400);button("标签："+LandmarkSystem.TAGS.get(tagIndex),30,145,100,()->{draftName=name.getValue();draftDescription=description.getValue();tagIndex=(tagIndex+1)%5;init();});button("确认上传/保存",30,180,120,()->{JsonObject q=query();q.addProperty("op",editId==0?"create":"edit");q.addProperty("id",editId);q.addProperty("name",name.getValue());q.addProperty("description",description.getValue());q.addProperty("tag",LandmarkSystem.TAGS.get(tagIndex));LandmarkClient.send(q);});button("取消",160,180,65,()->{editing=false;init();});}
 @Override public boolean mouseScrolled(double x,double y,double horizontal,double vertical){if(!editing&&vertical!=0){int next=Math.clamp(offset+(vertical>0?-1:1),0,Math.max(0,total-capacity()));if(next!=offset){offset=next;request("list",0);}return true;}return super.mouseScrolled(x,y,horizontal,vertical);}
 @Override public void extractRenderState(GuiGraphicsExtractor g,int mx,int my,float pt){g.fill(5,5,width-5,height-5,0xEE101D2C);super.extractRenderState(g,mx,my,pt);g.centeredText(font,title,width/2,10,0xFF7DE5E0);if(editing){g.text(font,"名称（最多24字）",30,50,0xFFFFFFFF);g.text(font,"简介（最多200字）",30,95,0xFFFFFFFF);return;}
  int available=Math.max(30,width-330);for(int i=0;i<Math.min(capacity(),rows.size());i++){JsonObject e=rows.get(i).getAsJsonObject();int y=top()+i*30;String text="#"+e.get("id").getAsLong()+" "+e.get("name").getAsString();g.text(font,font.plainSubstrByWidth(text,available),12,y,0xFFFFFFFF);g.text(font,font.plainSubstrByWidth(e.get("tag").getAsString()+" · "+e.get("creator").getAsString(),available),12,y+12,0xFF9AADC2);if(mx>=12&&mx<12+available&&my>=y&&my<y+25)g.setTooltipForNextFrame(font,font.split(Component.literal(e.get("description").getAsString()+"\n"+e.get("dimension").getAsString()+" ["+e.get("x")+", "+e.get("y")+", "+e.get("z")+"]"),Math.max(16,Math.min(320,width-24))),mx,my);}
  g.text(font,(total==0?0:offset+1)+" / "+total,160,height-20,0xFFBBCBDD);
 }
 @Override public boolean isPauseScreen(){return false;}
}

