package com.eccraft.config;
import java.nio.file.*;
import java.io.*;
import java.util.*;
import com.google.gson.JsonParser;
/** Config files are generated mirrors of the defaults shipped with this mod version. */
public final class BundledDefaults {
 public static final Map<String,String> FILES=Map.ofEntries(Map.entry("archer_armor_defaults.json","archer_armor.json"),Map.entry("archer_defaults.json","archer.json"),Map.entry("combat_skills_defaults.json","combat_skills.json"),Map.entry("enchanting_table_defaults.json","enchanting_table.json"),Map.entry("knight_armor_defaults.json","knight_armor.json"),Map.entry("knight_defaults.json","knight.json"),Map.entry("legend_equipment_defaults.json","legend_equipment.json"),Map.entry("mage_armor_defaults.json","mage_armor.json"),Map.entry("staff_balance_defaults.json","staff_balance.json"));
 public static void initialize(){refresh(net.fabricmc.loader.api.FabricLoader.getInstance().getConfigDir().resolve("eccraft"));}
 public static void refresh(Path directory){
  try{
   // Read and parse every packaged file before touching any existing config.
   var contents=new LinkedHashMap<String,byte[]>();
   for(var entry:FILES.entrySet())try(var in=BundledDefaults.class.getResourceAsStream("/data/eccraft/"+entry.getKey())){
    if(in==null)throw new IOException("Missing bundled configuration: "+entry.getKey());
    byte[] bytes=in.readAllBytes();if(!JsonParser.parseString(new String(bytes,java.nio.charset.StandardCharsets.UTF_8)).isJsonObject())throw new IOException("Expected config object: "+entry.getKey());contents.put(entry.getValue(),bytes);
   }
   Files.createDirectories(directory);
   for(var entry:contents.entrySet()){
    Path dest=directory.resolve(entry.getKey()),temp=Files.createTempFile(directory,".eccraft-default-",".tmp");
    try{Files.write(temp,entry.getValue());try{Files.move(temp,dest,StandardCopyOption.ATOMIC_MOVE,StandardCopyOption.REPLACE_EXISTING);}catch(AtomicMoveNotSupportedException ex){Files.move(temp,dest,StandardCopyOption.REPLACE_EXISTING);}}
    finally{Files.deleteIfExists(temp);}
   }
  }catch(Exception e){throw new IllegalStateException("Unable to refresh bundled numeric configuration; startup stopped",e);}
 }
 private BundledDefaults(){}
}
