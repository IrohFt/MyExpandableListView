package com.demo.expandablelistviewdemo.bean;

/**
 * @项目名： ExpandableListViewDemo
 * @包名： com.demo.expandablelistviewdemo.bean
 * @文件名: ChildBean
 * @创建者: Administrator
 * @创建时间: 2016-9-3 14:51
 * @描述： TODO
 */
public class ChildBean {
    private String week;
    private String activities;

    public ChildBean(String week, String activities) {
        this.week = week;
        this.activities = activities;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getActivities() {
        return activities;
    }


    public void setActivities(String activities) {
        this.activities = activities;
    }
}
