package com.demo.expandablelistviewdemo.base;

import android.content.Context;

import com.demo.expandablelistviewdemo.View.AnimatedExpandableListView;
import com.demo.expandablelistviewdemo.bean.ChildBean;
import com.demo.expandablelistviewdemo.bean.GroupBean;

import java.util.List;

/**
 * @项目名： ExpandableListViewDemo
 * @包名： com.demo.expandablelistviewdemo.base
 * @文件名: MyBaseExAdapter.java
 * @创建者: F.t
 * @创建时间: 2016-9-3 10:56
 * @描述： TODO
 */
public abstract class MyAnimBaseExAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    protected List<GroupBean> mDatas;
    protected  Context mContext;
    public MyAnimBaseExAdapter(List<GroupBean> data, Context context) {
        mDatas = data;
        mContext = context;
    }


    @Override
    public int getGroupCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        if (mDatas != null) {
            List<ChildBean> childrens = mDatas.get(groupPosition).getChildrens();
            return childrens != null ? childrens.size() : 0;
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mDatas != null ? mDatas.get(groupPosition) : null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (mDatas != null) {
            List<ChildBean> childrens = mDatas.get(groupPosition).getChildrens();
            return childrens != null ? childrens.get(childPosition) : null;
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }
}
