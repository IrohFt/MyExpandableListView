package com.demo.expandablelistviewdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.expandablelistviewdemo.R;
import com.demo.expandablelistviewdemo.base.MyBaseExAdapter;
import com.demo.expandablelistviewdemo.bean.ChildBean;
import com.demo.expandablelistviewdemo.bean.GroupBean;

import java.util.List;

/**
 * @项目名： ExpandableListViewDemo
 * @包名： com.demo.expandablelistviewdemo.adapter
 * @文件名: MyExAdapter
 * @创建者: Administrator
 * @创建时间: 2016-9-3 13:53
 * @描述： TODO
 */
public class MyExAdapter extends MyBaseExAdapter {

    private boolean mChildClickEnable;

    public MyExAdapter(List<GroupBean> data, Context context) {
        super(data, context);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_group, null);
            groupHolder = new GroupHolder(convertView);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }

        GroupBean groupBean = mDatas.get(groupPosition);
        groupHolder.tv_title.setText(groupBean.getTitle());
        groupHolder.tv_des.setText(groupBean.getDesc());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_child, null);
            childHolder = new ChildHolder(convertView);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        ChildBean childBean = mDatas.get(groupPosition).getChildrens().get(childPosition);
        childHolder.child_tv_week.setText(childBean.getWeek());
        childHolder.child_tv_activities.setText(childBean.getActivities());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return mChildClickEnable;
    }

    public boolean isChildClickEnable() {
        return mChildClickEnable;
    }

    public void setChildClickEnable(boolean childClickEnable) {
        mChildClickEnable = childClickEnable;
    }

    static class GroupHolder {
        TextView tv_title;
        TextView tv_des;
        ImageView iv_arrow;

        public GroupHolder(View view) {
            tv_title = (TextView) view.findViewById(R.id.group_tv_title);
            tv_des = (TextView) view.findViewById(R.id.group_tv_desc);
            iv_arrow = (ImageView) view.findViewById(R.id.group_iv_arrow);
        }
    }

    static class ChildHolder {
        TextView child_tv_week;
        TextView child_tv_activities;

        public ChildHolder(View view) {
            child_tv_week = (TextView) view.findViewById(R.id.child_tv_week);
            child_tv_activities = (TextView) view.findViewById(R.id.child_tv_activities);
        }
    }

}
