package com.whqfl.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuTree {
    //一级节点
    private String text;
    private String icon;
    private int nodeid;
    private int pid;
    private int sort;
    private Map<String, Object> state;
    //二级节点
    private List<MenuTree> nodes;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getNodeid() {
        return nodeid;
    }

    public void setNodeid(int nodeid) {
        this.nodeid = nodeid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<MenuTree> getTreeMenuList() {
        return nodes;
    }

    public void setTreeMenuList(List<MenuTree> treeMenuList) {
        this.nodes = treeMenuList;
    }

    public void setState(String res) {
        Map<String, Object> stateMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(res)) {
            stateMap.put("checked", true);
            stateMap.put("expanded", true);
        } else {
            stateMap.put("checked", false);
            stateMap.put("expanded", false);
        }
        this.state = stateMap;
    }

    @Override
    public String toString() {
        return "MenuTree{" +
                "text='" + text + '\'' +
                ", icon='" + icon + '\'' +
                ", nodeid=" + nodeid +
                ", pid=" + pid +
                ", sort=" + sort +
                ", state=" + state +
                ", nodes=" + nodes +
                '}';
    }
}
