package com.feiyahan.hanfei.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA <br/>
 * User: hanfei <br/>
 * Date:2016/3/3 17:20 <br/>
 * Version:V1.0 <br/>
 */
public class TreeViewEntity implements Serializable {

    private int id;
    private String name;
    private int pid;

    private String text;
    private HashMap<String,Object> extendible;
    private List<TreeViewEntity> nodes;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HashMap<String, Object> getExtendible() {
        return extendible;
    }

    public void setExtendible(HashMap<String, Object> extendible) {
        this.extendible = extendible;
    }

    public List<TreeViewEntity> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeViewEntity> nodes) {
        this.nodes = nodes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public List<TreeViewEntity> getTreeViewEntity(List<TreeViewEntity> treeList){
        List<TreeViewEntity> newTree= new ArrayList<TreeViewEntity>();
        for (int i=0;i<treeList.size();i++){
            TreeViewEntity tve=treeList.get(i);
            if(tve.getPid()==0){
                newTree.add(tve);
                treeList.remove(i);
            }
        }
        return newTree;
    }

    private void getChildrenTVE(List<TreeViewEntity> childrenTVE,TreeViewEntity parentTVE){

//        return parentTVE;
    }
}
