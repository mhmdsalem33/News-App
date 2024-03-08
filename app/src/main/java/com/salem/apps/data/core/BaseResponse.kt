package com.salem.apps.data.core

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
open class BaseResponse(
    @SerializedName("message")
    val message : String
)
