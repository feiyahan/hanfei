package com.feiyahan.hanfei.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;


public class TreeJsonNode implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int id ;
    private int pid ;
    private String text ;
    private String href ;
    private String[] tags;
    private Map<String,Object> state;
    private List<TreeJsonNode> nodes = new ArrayList<TreeJsonNode>() ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public Map<String, Object> getState() {
        return state;
    }

    public void setState(Map<String, Object> state) {
        this.state = state;
    }

    public List<TreeJsonNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeJsonNode> nodes) {
        this.nodes = nodes;
    }


    /**
     * 将TreeNode的list结构转换成easyUi需要的树结构
     * @param list
     * @return
     */
    public static List formatTree(List<TreeJsonNode> list) {
        List<TreeJsonNode> treelist = new ArrayList<TreeJsonNode>();// 封装好的json格式的数据
        /**
         * 找根节点
         */
        /*for (int i=0;i<list.size();i++){
            TreeJsonNode node = list.get(i);
            if("".equals(node.getPid()) || 0==node.getPid()){
                treelist.add(node);       //多根节点树
                list.remove(node);
//                i--;
            }
        }*/

        for(TreeJsonNode node:list){
            if("".equals(node.getPid()) || 0==node.getPid()){
                treelist.add(node);       //多根节点树
                list.remove(node);
            }
        }
        /**
         * 找子节点
         */
        for(int m = 0;m<treelist.size();m++) {
            TreeJsonNode root = treelist.get(m);
            getChildrenNodes(list, root); //找root的子节点
        }
        return treelist ;
    }

    /**]
     * 找某个节点的子节点
     * @param destList
     * @param node
     * @return
     */
    private static void getChildrenNodes(List<TreeJsonNode> destList,TreeJsonNode node){
        List resList = new ArrayList();
        for (TreeJsonNode tn :destList){
            if(node.getId()==tn.getPid()){
                resList.add(tn);
                destList.remove(tn);
                getChildrenNodes(destList,tn);
            }
        }
        node.setNodes(resList);
    }


    /**
     * 将数据库查询出的实体切换成树型列表
     * @param permList
     * @return
     */
    public static List getTreeJsonNodes(List<TreeJsonNodeFormater> permList) {
        List treeNodeList = new CopyOnWriteArrayList();
        for (TreeJsonNodeFormater o :permList){
            treeNodeList.add(o.toTreeJsonNode());
        }
        return treeNodeList;
    }


    @Override
    public String toString() {
        return "{id:"+id+"    pid:"+pid+"    text:"+text+"}";
    }
}