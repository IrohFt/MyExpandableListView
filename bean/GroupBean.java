package com.demo.expandablelistviewdemo.bean;

import java.util.List;

/**
 * @项目名： ExpandableListViewDemo
 * @包名： com.demo.expandablelistviewdemo.bean
 * @文件名: GroupBean
 * @创建者: Administrator
 * @创建时间: 2016-9-3 13:33
 * @描述： TODO
 */
public class GroupBean {
    private String title;
    private String desc;
    private List<ChildBean> childrens;
    private boolean isOpen;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public GroupBean(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<ChildBean> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<ChildBean> childrens) {
        this.childrens = childrens;
    }

}
