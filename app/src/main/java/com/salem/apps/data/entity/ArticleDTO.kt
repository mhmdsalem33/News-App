package com.salem.apps.data.entity


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ArticleDTO(
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("content")
    val content: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("publishedAt")
    val publishedAt: String? = "",
    @SerializedName("source")
    val source: SourceDTO? = SourceDTO(),
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("url")
    val url: String ?= "",
    @SerializedName("urlToImage")
    val urlToImage: String? = ""
)