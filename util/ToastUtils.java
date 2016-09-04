package com.demo.expandablelistviewdemo.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @项目名： ExpandableListViewDemo
 * @包名： com.demo.expandablelistviewdemo.util
 * @文件名: ToastUtils
 * @创建者: Administrator
 * @创建时间: 2016-9-3 18:51
 * @描述： TODO
 */
public class ToastUtils {
    private static Toast mToast;

    public static void toastShort(Context context,String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg + "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg + "");
        mToast.show();
    }
}
