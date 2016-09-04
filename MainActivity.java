package com.demo.expandablelistviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.demo.expandablelistviewdemo.View.AnimatedExpandableListView;
import com.demo.expandablelistviewdemo.adapter.MyAnimExAdapter;
import com.demo.expandablelistviewdemo.bean.ChildBean;
import com.demo.expandablelistviewdemo.bean.GroupBean;
import com.demo.expandablelistviewdemo.demo.LeakSingle;
import com.demo.expandablelistviewdemo.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener {

//    private ExpandableListView mExListView;
    private AnimatedExpandableListView mAnimatedExListView;
//    private MyExAdapter mMyExAdapter;
    private MyAnimExAdapter mMyAnimExAdapter;
    private List<GroupBean> mDatas;
    private Random mRan = new Random();
    private int mCurrentExpandGroup = -1;
    private boolean isSingle;
    private CheckBox mCb_single;
    private CheckBox mCb_isClick;
    private Animation mRotateAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        testLeak();
    }

    private void testLeak() {
        TextView hello = (TextView) findViewById(R.id.hello);
        LeakSingle.getInstance(this.getApplicationContext()).setRetainedTextView(hello);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LeakSingle.getInstance(this.getApplication()).removeRetainedTextView();
    }

    private void init() {
//        mExListView = (ExpandableListView) findViewById(R.id.expandable_listview);
        mAnimatedExListView = (AnimatedExpandableListView) findViewById(R.id.animated_expandable_listview);
        mCb_single = (CheckBox) findViewById(R.id.cb_single);
        mCb_isClick = (CheckBox) findViewById(R.id.cb_isclick);
        initData();


//        mExListView.setOnGroupClickListener(this);
//        mExListView.setOnChildClickListener(this);
        mAnimatedExListView.setOnGroupClickListener(this);
        mAnimatedExListView.setOnChildClickListener(this);
        mCb_single.setOnCheckedChangeListener(this);
        mCb_isClick.setOnCheckedChangeListener(this);

    }

    private void initData() {
        mDatas = new ArrayList<>();
        GroupBean groupBean;
        ChildBean childBean;
        for (int i = 1; i <= 10; i++) {
            groupBean = new GroupBean("This is Title :" + i, "Desc :" + i);
            List<ChildBean> childList = new ArrayList<>();
            for (int j = 1; j <= mRan.nextInt(7) + 1; j++) {
                childBean = new ChildBean("Week :" + j, "Activites :" + j);
                childList.add(childBean);
            }
            groupBean.setChildrens(childList);
            mDatas.add(groupBean);
        }
//        mMyExAdapter = new MyExAdapter(mDatas, getApplicationContext());
//        mMyExAdapter.setChildClickEnable(mCb_isClick.isChecked());
        mMyAnimExAdapter = new MyAnimExAdapter(mDatas, getApplicationContext());
        mMyAnimExAdapter.setChildClickEnable(mCb_isClick.isChecked());
//        mExListView.setAdapter(mMyExAdapter);
//        mAnimatedExListView.setAdapter(mMyExAdapter);
        mAnimatedExListView.setAdapter(mMyAnimExAdapter);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_single:
//                if (mExListView != null && mDatas != null) {
                if (mAnimatedExListView != null && mDatas != null) {
                    for (int i = 0; i < mDatas.size(); i++) {
                        //expandGroup全部展开
//                        mExListView.expandGroup(i);
                        //collapseGroup全部收缩
//                        mExListView.collapseGroup(i);
                        mAnimatedExListView.collapseGroupWithAnimation(i);
                        mDatas.get(i).setOpen(false);
                        isSingle = isChecked;
                    }
                }
                mMyAnimExAdapter.notifyDataSetChanged();
                break;
            case R.id.cb_isclick:
                /*if (mMyExAdapter != null) {
                    mMyExAdapter.setChildClickEnable(isChecked);
                }*/
                if (mMyAnimExAdapter != null) {
                    mMyAnimExAdapter.setChildClickEnable(isChecked);
                }
                break;
        }
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        if (isSingle) {
            if (mCurrentExpandGroup == -1) {
//                mExListView.expandGroup(groupPosition);
                mAnimatedExListView.expandGroupWithAnimation(groupPosition);
                mDatas.get(groupPosition).setOpen(true);
//                arrowOpenAnim(iv_arrow);
                mCurrentExpandGroup = groupPosition;
            } else {
                if (mCurrentExpandGroup == groupPosition) {
//                    mExListView.collapseGroup(groupPosition);
                    mAnimatedExListView.collapseGroupWithAnimation(groupPosition);
                    mDatas.get(groupPosition).setOpen(false);
                    mCurrentExpandGroup = -1;
                } else {
//                    mExListView.collapseGroup(mCurrentExpandGroup);
//                    mExListView.expandGroup(groupPosition);
                    mAnimatedExListView.collapseGroupWithAnimation(mCurrentExpandGroup);
                    mAnimatedExListView.expandGroupWithAnimation(groupPosition);
                    mDatas.get(mCurrentExpandGroup).setOpen(false);
                    mDatas.get(groupPosition).setOpen(true);
                    mCurrentExpandGroup = groupPosition;
                }
            }
        } else {
            if (mAnimatedExListView.isGroupExpanded(groupPosition)) {
                mAnimatedExListView.collapseGroupWithAnimation(groupPosition);
                mDatas.get(groupPosition).setOpen(false);
            } else {
                mAnimatedExListView.expandGroupWithAnimation(groupPosition);
                mDatas.get(groupPosition).setOpen(true);
            }
        }
        return true;
    }


    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        ToastUtils.toastShort(getApplicationContext(), (groupPosition + 1) + " --> " + (childPosition + 1));
        return true;
    }
}
