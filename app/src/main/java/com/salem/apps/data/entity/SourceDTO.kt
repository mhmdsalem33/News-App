package com.salem.apps.data.entity


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class SourceDTO(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String? = ""
)