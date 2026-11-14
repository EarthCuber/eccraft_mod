package com.eccraft.armor.client;

import com.eccraft.client.gltf.GltfModel;
import java.util.*;
import org.joml.Vector3f;
import org.joml.Quaternionf;

/** Rigid limb binding for the supplied T2 chest whose original skin table is missing. */
public final class MageArmorT2Arms {
    public static final String LEFT="eccraft_t2_left_arm", RIGHT="eccraft_t2_right_arm";
    public static void hidePlayerArms(net.minecraft.client.model.player.PlayerModel model,net.minecraft.world.item.ItemStack chest) {
        if(chest.getItem() instanceof com.eccraft.armor.MageArmorItem armor && armor.tier()==2
                && armor.slot()==net.minecraft.world.entity.EquipmentSlot.CHEST) {
            model.leftArm.visible=false;model.rightArm.visible=false;
            model.leftSleeve.visible=false;model.rightSleeve.visible=false;
        }
    }
    public static boolean applies(GltfModel model,GltfModel.Mesh mesh) {
        return mesh.skin>=model.skins.size() && mesh.skin>=0 && mesh.node>=0
            && model.nodes.get(mesh.node).name.equals("m_m_t2_clothes");
    }
    public static void split(GltfModel target,GltfModel.Mesh source) {
        int count=source.vertexCount();int[] parent=new int[count];Map<String,Integer> positions=new HashMap<>();
        for(int i=0;i<count;i++) {
            parent[i]=i;
            String key=Math.round(source.positions[i*3]*10000)+","+Math.round(source.positions[i*3+1]*10000)+","+Math.round(source.positions[i*3+2]*10000);
            Integer first=positions.putIfAbsent(key,i);if(first!=null)parent[i]=first;
        }
        for(int i=0;i<source.indices.length;i+=3)for(int j=1;j<3;j++)parent[root(parent,source.indices[i+j])]=root(parent,source.indices[i]);
        Map<Integer,float[]> bounds=new HashMap<>();
        for(int i=0;i<count;i++) {
            float[] b=bounds.computeIfAbsent(root(parent,i),k->new float[]{Float.POSITIVE_INFINITY,Float.NEGATIVE_INFINITY});
            b[0]=Math.min(b[0],source.positions[i*3]);b[1]=Math.max(b[1],source.positions[i*3]);
        }
        List<Integer> body=new ArrayList<>(),left=new ArrayList<>(),right=new ArrayList<>();
        for(int i=0;i<source.indices.length;i+=3) {
            float[] b=bounds.get(root(parent,source.indices[i]));
            List<Integer> output=b[0]>=2.19F && b[1]>3?left:b[1]<=-2.19F && b[0]<-3?right:body;
            for(int j=0;j<3;j++)output.add(source.indices[i+j]);
        }
        if(left.isEmpty() || right.isEmpty() || body.isEmpty())throw new IllegalArgumentException("T2 chest no longer matches rigid arm layout; re-export a valid skin");
        add(target,source,body,source.node);add(target,source,left,node(target,LEFT));add(target,source,right,node(target,RIGHT));
    }
    private static int root(int[] parents,int i) { while(parents[i]!=i){parents[i]=parents[parents[i]];i=parents[i];}return i; }
    private static int node(GltfModel target,String name) {
        int index=target.nodes.size();target.nodes.add(new GltfModel.Node(name,new Vector3f(),new Quaternionf(),new Vector3f(1)));target.roots.add(index);return index;
    }
    private static void add(GltfModel target,GltfModel.Mesh source,List<Integer> indices,int node) {
        var mesh=new GltfModel.Mesh(source.positions,source.normals,source.uvs,indices.stream().mapToInt(Integer::intValue).toArray(),null,null);
        mesh.node=node;target.meshes.add(mesh);
    }
    private MageArmorT2Arms() {}
}
