package com.lxw.mileageselector;

import android.content.Context;

/**
 * Created by paul on 2018/7/22.
 * Description:
 */
public class ToolUtil {
    /**
     * dp转换成px
     */
    public static int dp2px(float dpValue) {
        float scale = App.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转换成dp
     */
    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
