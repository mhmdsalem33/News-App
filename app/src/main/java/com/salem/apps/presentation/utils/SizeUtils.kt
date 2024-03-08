package com.alexon.delivary.presentation.util

import android.content.Context

fun px2Dp(   context: Context , px: Int ): Int {
    return ( px / context.resources.displayMetrics.density).toInt()
}

fun dp2Px(   context: Context , dp: Int  ): Int {
    return ( dp * context.resources.displayMetrics.density).toInt()
}
