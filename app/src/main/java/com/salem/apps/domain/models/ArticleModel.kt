package com.salem.apps.domain.models


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class ArticleModel(
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val source: SourceModel? = SourceModel(),
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String? = ""
)