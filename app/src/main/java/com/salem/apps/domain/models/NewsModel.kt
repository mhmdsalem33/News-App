package com.salem.apps.domain.models


import androidx.annotation.Keep


@Keep
data class NewsModel(
    val articles     : List<ArticleModel> ? = emptyList(),
    val status       : String? ="",
    val totalResults : Int? = 0
)