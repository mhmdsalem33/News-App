package com.salem.apps.presentation.ui.main.activity.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.salem.apps.data.core.ResponseState
import com.salem.apps.domain.models.ArticleModel
import com.salem.apps.presentation.ui.main.activity.MainViewModel
import com.salem.apps.presentation.ui.main.activity.compose.theme.AppsTheme
import com.salem.apps.presentation.ui.main.activity.compose.theme.omnesArabic
import com.salem.apps.presentation.ui.main.activity.compose.theme.omnesArabicBold
import com.salem.apps.presentation.widget.LoadingIndicator
import com.salem.apps.presentation.widget.RoundImage
import com.salem.apps.presentation.widget.SearchTextField
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppsTheme {
                Column {
                    SearchTextField()
                    NewsScreen()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppsTheme {}
}


@Composable
fun NewsScreen(viewModel: MainViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.getBitCoinsNews("bitcoin")
    }
    val responseState by viewModel.getBitCoinsNewsResponse.collectAsState()

    when (responseState) {
        is ResponseState.Loading -> {
            LoadingIndicator()

        }
        is ResponseState.Success -> {
            val articlesList = responseState.data?.articles ?: emptyList()
            if (articlesList.isNotEmpty()) {
                NewsList(articlesList)
            }
        }

        is ResponseState.Error -> {
            ErrorScreen(error = responseState.message ?: "")
        }

        else -> Unit
    }
}

@Composable
fun NewsList(articles: List<ArticleModel>) {
    LazyColumn {
        items(articles) { newsItem ->
            NewsItem(newsItem)
        }
    }
}

@Composable
fun NewsItem(newsItem: ArticleModel) {
    Column(modifier = Modifier.padding(vertical = 5.dp)) {
        // article image
        RoundImage(newsItem.urlToImage ?: "")
        SpaceHeight()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // text author
            Text(
                text = newsItem.author ?: "",
                modifier = Modifier.padding(start = 20.dp),
                fontFamily = omnesArabicBold,
                fontSize = 14.sp
            )
            // text source
            Text(
                text = newsItem.source?.name ?: "",
                modifier = Modifier.padding(end = 20.dp),
                fontFamily = omnesArabic,
                fontSize = 12.sp
            )
        }
        SpaceHeight()
        // text title
        Text(
            text = newsItem.title ?: "",
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            fontFamily = omnesArabicBold,
            fontSize = 10.sp
        )
        SpaceHeight()
        // text description
        Text(
            text = newsItem.description ?: "",
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            fontFamily = omnesArabic,
            fontSize = 10.sp
        )
    }
}

@Composable
fun SpaceHeight() {
    Spacer(modifier = Modifier.height(10.dp))
}


@Composable
fun ErrorScreen(error: String) {
    Text(
        text = "An error occurred: $error",
        fontFamily = omnesArabic,
        fontSize = 12.sp,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}


