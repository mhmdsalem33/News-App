package com.salem.apps.data.entity


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.salem.apps.domain.models.ArticleModel
import com.salem.apps.domain.models.NewsModel
import com.salem.apps.domain.models.SourceModel


@Keep
data class NewsDTO(
    @SerializedName("articles")
    val articles: List<ArticleDTO>? = emptyList(),
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("totalResults")
    val totalResults: Int? = 0
)


fun NewsDTO.toDomain(): NewsModel {
    return NewsModel(
        articles = this.articles?.map {  it.toDomain()  } ,
        status = this.status,
        totalResults = this.totalResults,
    )
}

fun ArticleDTO.toDomain(): ArticleModel {
    return ArticleModel(
        author      = this.author,
        content     = this.content,
        urlToImage  = this.urlToImage,
        description = this.description,
        publishedAt = this.publishedAt,
        title       = this.title,
        source      = this.source?.toDomain()
    )
}


fun SourceDTO.toDomain() : SourceModel{
    return SourceModel(
        name = this.name ?: "",
        id   = this.id ?: ""
    )
}

