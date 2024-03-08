package com.salem.apps.domain.models


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class SourceModel(
    val id: String? = "",
    val name: String = ""
)