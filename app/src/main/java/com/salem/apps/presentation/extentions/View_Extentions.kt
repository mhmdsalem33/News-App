package com.salem.apps.presentation.extentions
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import de.hdodenhof.circleimageview.CircleImageView


fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.isVisible(isShow: Boolean, vararg container: View) {
    if (isShow) {
        this.visibility = View.VISIBLE // my current view will be still visible
        container.forEach {
            it.visibility = View.GONE  // here will other views will be hide
        }
    } else {
        this.visibility = View.GONE
        container.forEach {
            it.visibility = View.VISIBLE
        }
    }
}

fun View.showSnackBar(message: String) = Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
fun View.click(block: (View) -> Unit)  = setOnClickListener { view -> block(view) }


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}


fun View.toggleVisibility() {
    visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}



fun Context.getStringFromResource(resourceId: Int): String {
    return this.resources.getString(resourceId)
}

fun ImageView.loadImage(url: String) {
    Glide.with(context).load(url).into(this)
}

fun CircleImageView.loadImage(url: String) {
    Glide.with(context).load(url).into(this)
}

fun ImageView.loadDrawable(@DrawableRes resourceId: Int) {
    setImageResource(resourceId)
}

fun CircleImageView.loadDrawable(@DrawableRes resourceId: Int) {
    setImageResource(resourceId)
}


// Inflate ViewBinding in a more concise way for Fragment
inline fun <reified T : ViewBinding> Fragment.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater.invoke(layoutInflater)
}

// Access ViewBinding directly from Fragment
inline fun <T : ViewBinding> Fragment.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T,
    crossinline initializer: T.() -> Unit = {}
) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater.invoke(layoutInflater).apply { initializer() }
}


inline fun <reified T : ViewBinding> Activity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater.invoke(layoutInflater)
}



fun AppCompatActivity.preventScreenCapture() {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_SECURE,
        WindowManager.LayoutParams.FLAG_SECURE
    )
}
