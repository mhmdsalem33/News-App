package com.salem.apps.presentation.component

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.text.InputFilter
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Space
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.ImageViewCompat
import androidx.core.widget.addTextChangedListener
import com.alexon.delivary.presentation.util.dp2Px
import com.salem.apps.R
import com.salem.apps.presentation.utils.EditTextUtils


class InputField(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private var myContext: Context? = null
    private val defaultTextColor: Int = Color.BLACK
    private val defaultHintTextColor: Int = Color.GRAY
    private var iconLeft: ImageView
    private var editText: EditText
    private var iconShowPassword: ImageView
    private var iconHidePassword: ImageView
    private var iconClearEdtText: ImageView
    private var linearHorizontal: LinearLayout
    private var linearH: LinearLayout
    private val linearShowPasswords: LinearLayout
    private var textView: TextView
    private var space: Space
    private var isShowPasswordClicked: Boolean = false
    private var progressBar : ProgressBar


    init {
        myContext = context
        orientation = VERTICAL
        iconLeft = ImageView(myContext)
        editText = EditText(myContext)
        iconShowPassword = ImageView(myContext)
        iconHidePassword = ImageView(myContext)
        iconClearEdtText = ImageView(context)
        linearHorizontal = LinearLayout(myContext)
        linearShowPasswords = LinearLayout(myContext)
        textView = TextView(context)
        progressBar = ProgressBar(context)
        linearH = LinearLayout(context)

        space = Space(context)
        build(attrs = attrs)
    }

    fun build(attrs: AttributeSet?) {

        // Read custom attributes
        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.InputField)
        val textColor = typedArray?.getColor(R.styleable.InputField_textColor, defaultTextColor)
        val textPhoneColor =
            typedArray?.getColor(R.styleable.InputField_textPhoneColor, defaultTextColor)
        val hintTextColor =
            typedArray?.getColor(R.styleable.InputField_hintTextColor, defaultHintTextColor)
        val text = typedArray?.getString(R.styleable.InputField_text)
        val hint = typedArray?.getString(R.styleable.InputField_hint)
        val editTextMaxLines = typedArray?.getInt(R.styleable.InputField_editTextMaxLines, 1) ?: 1
        val editTextMaxLength =
            typedArray?.getInt(R.styleable.InputField_editTextMaxLength, Int.MAX_VALUE)
                ?: Int.MAX_VALUE
        val editTextBackground = typedArray?.getDrawable(R.styleable.InputField_editTextBackground)
        val mainLayoutBackground =
            typedArray?.getDrawable(R.styleable.InputField_mainLayoutBackground)
        val mainLayoutPadding =
            typedArray?.getDimensionPixelSize(R.styleable.InputField_mainLayoutPadding, 0) ?: 0
        val iconLeftTopMargin =
            typedArray?.getDimensionPixelSize(R.styleable.InputField_iconLeftTopMargin, 0) ?: 0
        val textPhoneMarginTop =
            typedArray?.getDimensionPixelSize(R.styleable.InputField_textPhoneMarginTop, 0) ?: 6

        val editTextFontFamily =
            typedArray?.getResourceId(R.styleable.InputField_editTextFontFamily, 0) ?: 0
        val leftIconSrc = typedArray?.getResourceId(R.styleable.InputField_leftIconSrc, 0) ?: 0
        val rightImageShowPassword =
            typedArray?.getResourceId(R.styleable.InputField_iconShowPasswordSrc, 0) ?: 0
        val iconHidePasswordSrc =
            typedArray?.getResourceId(R.styleable.InputField_iconHidePasswordSrc, 0) ?: 0
        val iconClearEdtTextSrc =
            typedArray?.getResourceId(R.styleable.InputField_iconClearEdtTextSrc, 0) ?: 0
        val iconShowPasswordTintColor =
            typedArray?.getResourceId(R.styleable.InputField_iconTintShowPassword, 0) ?: 0
        val iconHidePasswordTintColor =
            typedArray?.getResourceId(R.styleable.InputField_iconTintHidePassword, 0) ?: 0
        val iconTintFocused =
            typedArray?.getResourceId(R.styleable.InputField_iconTintFocused, 0) ?: 0
        val iconTintUnFocused =
            typedArray?.getResourceId(R.styleable.InputField_iconTintUnFocused, 0) ?: 0
        val iconTintClearEdtText =
            typedArray?.getResourceId(R.styleable.InputField_iconTintClearEdtText, 0) ?: 0
        val textPhone = typedArray?.getString(R.styleable.InputField_textPhone)
        val textPhoneSize =
            typedArray?.getDimensionPixelSize(R.styleable.InputField_textPhoneSize, 30) ?: 30
        val inputTypeFlags = typedArray?.getInt(R.styleable.InputField_inputTypeEdt, 0) ?: 0
        val cursorDrawable =
            typedArray?.getResourceId(R.styleable.InputField_cursorDrawable, 0) ?: 0
        val textAlignmentValue = typedArray?.getInt(
            R.styleable.InputField_editTextTextAlignment,
            View.TEXT_ALIGNMENT_VIEW_START
        )
            ?: View.TEXT_ALIGNMENT_VIEW_START
        val cursorColor = typedArray?.getColor(R.styleable.InputField_cursorColor, Color.BLACK)
        val textPhoneStyle = typedArray?.getInt(R.styleable.InputField_textPhoneStyle, 0) ?: 0
        val fontFamilyResId =
            typedArray?.getResourceId(R.styleable.InputField_textPhoneFontFamily, 0) ?: 0

        val edtTextSize = typedArray?.getDimension(R.styleable.InputField_edtTextSize , -1f)


        val leftIconTint = typedArray?.getColorStateList(R.styleable.InputField_leftIconTint)



        Log.e("testInput", inputTypeFlags.toString())

        typedArray?.recycle()


        // Main linearHorizontal
        linearHorizontal.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        linearHorizontal.gravity = Gravity.CENTER_VERTICAL
        linearHorizontal.orientation = HORIZONTAL
        linearHorizontal.background = mainLayoutBackground // Set background for main LinearLayout
        linearHorizontal.setPadding(
            mainLayoutPadding,
            mainLayoutPadding,
            mainLayoutPadding,
            mainLayoutPadding
        ) // Set padding
        addView(linearHorizontal)

        // left icon and text linearH
        linearH.layoutParams = LayoutParams(
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )

        linearH.orientation = HORIZONTAL
        linearHorizontal.addView(linearH)

        // if edt text input type is not text multi line set margin
        if (inputTypeFlags != 131073)
        {
            setMargin()
        }

        Log.e("testInput" , "input  "+    inputTypeFlags.toString())

        val iconSize = myContext?.let { dp2Px(it, 15 ) }

        // Left icon
        iconLeft.setImageResource(leftIconSrc)
        iconLeft.layoutParams = LayoutParams(iconSize ?: 0, iconSize ?: 0).apply {
            setMargins(
                0,
                dp2Px(myContext!!, iconLeftTopMargin),
                0,
                0
            )// margin from start to icon left
            gravity = Gravity.CENTER_VERTICAL // Center vertically

        }
        if (leftIconSrc == 0) {
            iconLeft.visibility = View.GONE
        } else {
            iconLeft.visibility = View.VISIBLE
        }

        if (leftIconTint != null) {
            iconLeft.imageTintList = leftIconTint
        }

        linearH.addView(iconLeft)
        space.layoutParams = LayoutParams(
            dp2Px(myContext!!, 7),
            ViewGroup.LayoutParams.WRAP_CONTENT

        )

        linearH.addView( space )




        // phone text
        textView.layoutParams = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, dp2Px(context, textPhoneMarginTop), 0, 0)

        }
        textView.text = textPhone
        textView.setTextColor(textPhoneColor ?: defaultTextColor)
        textView.gravity = Gravity.CENTER_VERTICAL
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textPhoneSize.toFloat())
        // Set font family

        setTextStyle(textPhoneStyle, fontFamilyResId)


        linearH.addView(textView)


        // loading progress bar
        progressBar.layoutParams = LayoutParams(
            dp2Px(context , 20),
            dp2Px(context , 20)
        ).apply {
            setMargins(0, dp2Px(context, textPhoneMarginTop), 0, 0)
        }
        val tintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.blue))
        progressBar.indeterminateTintList = tintList
        progressBar.visibility = View.GONE
        linearH.addView(progressBar)
        

        // editedText
        editText.layoutParams = LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
            weight = 1f
            setMargins(dp2Px(myContext!!, 2 ), 7, dp2Px(myContext!!, 8), 0)
        }
        editText.setText(text)
        editText.hint = hint
        editText.setTextColor(textColor ?: defaultTextColor)
        editText.setHintTextColor(hintTextColor ?: defaultHintTextColor)
//        editTextBackground?.let { editText.background = it }
        editText.maxLines = editTextMaxLines

        editText.background = null

        editText.inputType = inputTypeFlags
        EditTextUtils.setCursorDrawable(context, editText, cursorDrawable)


        val filters = arrayOf<InputFilter>(InputFilter.LengthFilter(editTextMaxLength))
        editText.filters = filters
        if (edtTextSize != -1f) {
            editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, edtTextSize ?: 0f)
        }

        editText.textAlignment = textAlignmentValue

        // Set font family
        if (editTextFontFamily != 0) {
            editText.typeface = ResourcesCompat.getFont(context, editTextFontFamily)
        }
        linearHorizontal.addView(editText)

        // add Focus Listener
//        editText.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
//            if (hasFocus) {
//                linearHorizontal.setBackgroundResource(R.drawable.orange_border_with_radius)
//                if (iconTintFocused != 0) {
//                    iconLeft.setImageTintResource(iconTintFocused)
//                    iconShowPassword.setImageTintResource(iconTintFocused)
//                    iconHidePassword.setImageTintResource(iconTintFocused)
//                    iconClearEdtText.setImageTintResource(iconTintFocused)
//                }
//            } else {
//                linearHorizontal.setBackgroundResource(R.drawable.edt_login_background)
//                iconLeft.setImageTintResource(iconTintUnFocused)
//                iconShowPassword.setImageTintResource(iconTintUnFocused)
//                iconHidePassword.setImageTintResource(iconTintUnFocused)
//                iconClearEdtText.setImageTintResource(iconTintUnFocused)
//            }
//        }


        // add listener for edt text
        editText.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                showEdtTextClearIcon(iconClearEdtTextSrc, inputTypeFlags)
                Log.e("testMax", "UP " + editText.lineCount.toString())
                if (editText.lineCount == 2) {
                    updateTopMarginOfIcons()
                } else if (editText.lineCount == 1) {
                    resetMarginOfIcons()
                }

            } else {
                hideRightIconsVisibility(iconClearEdtTextSrc)
            }
        }




        linearShowPasswords.layoutParams = LayoutParams(
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
        linearShowPasswords.orientation = HORIZONTAL
        linearHorizontal.addView(linearShowPasswords)

        // Right Image show password
        val iconRightSize = 85
        iconShowPassword.setImageResource(rightImageShowPassword)
        iconShowPassword.layoutParams = LayoutParams(iconRightSize ?: 0, iconRightSize ?: 0).apply {
            setMargins(
                dp2Px(myContext!!, 10),
                0,
                dp2Px(myContext!!, 2),
                0
            ) // margin from end to icon right
        }
        if (rightImageShowPassword == 0) {
            iconShowPassword.visibility = View.GONE
        } else {
            iconShowPassword.visibility = View.VISIBLE
        }
        iconShowPassword.visibility = View.GONE
        if (iconShowPasswordTintColor != 0) {
            iconShowPassword.setImageTintResource(iconShowPasswordTintColor)

        }
        iconShowPassword.setPadding(
            dp2Px(myContext!!, 8),
            dp2Px(myContext!!, 8),
            dp2Px(myContext!!, 8),
            dp2Px(myContext!!, 8)
        )


        linearShowPasswords.addView(iconShowPassword)


        // Right Image hide password
        iconHidePassword.setImageResource(iconHidePasswordSrc)
        iconHidePassword.layoutParams = LayoutParams(iconRightSize, iconRightSize).apply {
            setMargins(0, 0, dp2Px(myContext!!, 2), 0) // margin from end to icon right
        }
        iconHidePassword.visibility = View.GONE
        if (iconHidePasswordTintColor != 0) {
            iconHidePassword.setImageTintResource(iconHidePasswordTintColor)
        }
        iconHidePassword.setPadding(
            dp2Px(myContext!!, 8),
            dp2Px(myContext!!, 8),
            dp2Px(myContext!!, 8),
            dp2Px(myContext!!, 8)
        )
        linearShowPasswords.addView(iconHidePassword)


        val iconClearEdtTextSize = myContext?.let { dp2Px(it, 35) }

        // Right Image clear edt text
        iconClearEdtText.setImageResource(iconClearEdtTextSrc)
        if (iconTintClearEdtText != 0) {
            iconClearEdtText.setImageTintResource(iconTintClearEdtText)
        }
        iconClearEdtText.layoutParams =
            LayoutParams(iconClearEdtTextSize ?: 0, iconClearEdtTextSize ?: 0).apply {
                setMargins(0, 0, dp2Px(myContext!!, 2), 0) // margin from end to icon right
            }
        val paddingPx = dp2Px(myContext!!, 10)
        iconClearEdtText.setPadding(paddingPx, paddingPx, paddingPx, paddingPx)
        iconClearEdtText.visibility = View.GONE
        linearShowPasswords.addView(iconClearEdtText)

//        setRightMargin()

        // set click on show password
        iconShowPassword.click {
            editText.showPassword()
            iconShowPassword.visibility = View.GONE
            iconHidePassword.visibility = View.VISIBLE
            isShowPasswordClicked = !isShowPasswordClicked
            Log.e("testShow", "CLICKEd $isShowPasswordClicked")
        }

        // set click on hide password
        iconHidePassword.click {
            editText.hidePassword()
            iconShowPassword.visibility = View.VISIBLE
            iconHidePassword.visibility = View.GONE
            isShowPasswordClicked = !isShowPasswordClicked
            Log.e("testShow", "CLICKEd $isShowPasswordClicked")
        }

        // set click on clear edt text
        setOnClearTextClick {
            editText.text.clear()
        }

        // if icon clear edt text is not empty hide iconShowPassword , iconHidePassword and show iconClearEdtText
        hideRightIconsVisibility(iconClearEdtTextSrc)


        if (inputTypeFlags == 129 || inputTypeFlags == 225 || inputTypeFlags == 145 || inputTypeFlags == 18) {
            iconShowPassword.visibility = View.VISIBLE
        }

        Log.e("testInputS", inputTypeFlags.toString())




    }


    // Public method to set click listener for iconLeft
    fun setIconLeftCLickListener(listener: OnClickListener?) {
        iconLeft.setOnClickListener(listener)
    }

    // Public method to set click listener for editText
    fun setEditTextClickListener(listener: OnClickListener?) {
        editText.setOnClickListener(listener)
    }

    fun setOnClearTextClick(listener: OnClickListener?) {
        iconClearEdtText.setOnClickListener(listener)
    }

    // Public method to set click listener for iconShowPassword
    fun setIconShowPasswordClickListener(listener: OnClickListener?) {
        iconShowPassword.setOnClickListener(listener)

    }

    fun setHidePasswordClickListener(listener: OnClickListener?) {
        iconHidePassword.setOnClickListener(listener)
    }


    fun edtText() = editText

    val iconPhoneLeft = iconLeft
    val clearTextIcon =  iconClearEdtText
    val textPhone = textView
    val loadingCountriesProgressBar = progressBar
    val getMainLinear  = linearHorizontal


    private fun hideRightIconsVisibility(iconClearEdtTextSrc: Int) {
        if (iconClearEdtTextSrc != 0) {
            iconShowPassword.visibility = View.GONE
            iconHidePassword.visibility = View.GONE
            iconClearEdtText.visibility = View.GONE
        }
    }

    private fun showEdtTextClearIcon(iconClearEdtTextSrc: Int, inputTypeFlags: Int) {
        if (iconClearEdtTextSrc != 0) { // if icon clear is not empty and user is already put it in xml code
            // id edt text  type is password with 129 , 225 , 145 , 18
            if (inputTypeFlags == 129 || inputTypeFlags == 225 || inputTypeFlags == 145 || inputTypeFlags == 18) {
                if (isShowPasswordClicked) { // if use is already click to show password
                    iconShowPassword.visibility = View.GONE
                    iconHidePassword.visibility = View.VISIBLE
                    iconClearEdtText.visibility = View.GONE
                } else {
                    iconShowPassword.visibility = View.VISIBLE
                    iconHidePassword.visibility = View.GONE
                    iconClearEdtText.visibility = View.GONE
                }
            } else {
                iconShowPassword.visibility = View.GONE
                iconHidePassword.visibility = View.GONE
                iconClearEdtText.visibility = View.VISIBLE
            }
        }
    }

    private fun ImageView.setImageTintResource(@ColorRes colorResId: Int) {
        val color = ContextCompat.getColor(context, colorResId)
        ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(color))
    }

    private fun updateTopMarginOfIcons() {
        val layoutParams = linearH.layoutParams as LinearLayout.LayoutParams
        layoutParams.setMargins(0, dp2Px(context, 15), 0, 0)
        linearH.layoutParams = layoutParams
        linearShowPasswords.layoutParams = layoutParams
        linearHorizontal.gravity = Gravity.TOP

    }

    private fun resetMarginOfIcons() {
        val layoutParams = linearH.layoutParams as LinearLayout.LayoutParams
        layoutParams.setMargins(0, dp2Px(context, 0), 0, 0)
        linearH.layoutParams = layoutParams
        linearShowPasswords.layoutParams = layoutParams
        linearHorizontal.gravity = Gravity.CENTER_VERTICAL

    }

    private fun View.click(block: (View) -> Unit) = setOnClickListener { view -> block(view) }

    private fun EditText.showPassword() {
        if (transformationMethod != null) {
            val cursorPosition = selectionEnd
            transformationMethod = null
            text = text // Refresh the view
            setSelection(cursorPosition)
        }
    }

    private fun EditText.hidePassword() {
        if (transformationMethod == null) {
            val cursorPosition = selectionEnd
            transformationMethod = PasswordTransformationMethod.getInstance()
            text = text // Refresh the view
            setSelection(cursorPosition)
        }
    }

    private fun setTextStyle(style: Int, fontFamilyResId: Int) {
        val typeface = if (fontFamilyResId != 0) {
            ResourcesCompat.getFont(context, fontFamilyResId)
        } else {
            null
        }

        when (style) {
            1 -> textView.setTypeface(typeface, Typeface.BOLD)
            2 -> textView.setTypeface(typeface, Typeface.ITALIC)
            else -> textView.setTypeface(typeface, Typeface.NORMAL)
        }
    }

    private fun setMargin() {
        val frameLayoutParams = FrameLayout.LayoutParams(
            resources.getDimensionPixelSize(R.dimen.left_margin),
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        val frameLayout = FrameLayout(context)
        frameLayout.layoutParams = frameLayoutParams
        linearH.addView(frameLayout)
    }

}
