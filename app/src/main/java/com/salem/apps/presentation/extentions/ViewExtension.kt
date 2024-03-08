package com.salem.apps.presentation.extentions

import android.view.View

object ViewExtension {
    inline fun View.onSingleClick(crossinline action: (v: View) -> Unit ) {
        setOnClickListener { v ->
            isEnabled = false
            action(v)
            postDelayed( { isEnabled = true } , 500  )
        }
    }
}