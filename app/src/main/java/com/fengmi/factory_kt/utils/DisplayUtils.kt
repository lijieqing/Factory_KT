package com.fengmi.factory_kt.utils

import android.content.Context

object DisplayUtils {

    /**
     * px 转 dp
     * @param context context
     * @param px px
     * @return dp
     */
    fun px2dp(context: Context, px: Float): Float {
        val scale = context.resources.displayMetrics.density
        return px / scale + 0.5f
    }

    /**
     * dp 转 px
     * @param context context
     * @param dp dp
     * @return px
     */
    fun dp2px(context: Context, dp: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dp * scale + 0.5f
    }

    /**
     * px 转 sp
     * @param context context
     * @param px pixel
     * @return sp
     */
    fun px2sp(context: Context, px: Float): Float {
        val scale = context.resources.displayMetrics.scaledDensity
        return px / scale + 0.5f
    }

    /**
     * dp 转 px
     * @param context context
     * @param sp sp
     * @return px
     */
    fun sp2px(context: Context, sp: Float): Float {
        val scale = context.resources.displayMetrics.scaledDensity
        return sp * scale + 0.5f
    }
}
