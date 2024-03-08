package com.salem.apps.presentation.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.widget.EditText
import android.widget.TextView

object EditTextUtils {

    fun setCursorDrawable(context: Context, editText: EditText, cursorDrawableResId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            editText.setTextCursorDrawable(cursorDrawableResId)
        } else {
            // For versions below Q, you can use a workaround
            editText.setTextCursorDrawableCompat(context, cursorDrawableResId)
        }
    }

    @SuppressLint("SoonBlockedPrivateApi")
    private fun EditText.setTextCursorDrawableCompat(context: Context, cursorDrawableResId: Int) {
        try {
            val field = TextView::class.java.getDeclaredField("mCursorDrawableRes")
            field.isAccessible = true
            field.set(this, cursorDrawableResId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
