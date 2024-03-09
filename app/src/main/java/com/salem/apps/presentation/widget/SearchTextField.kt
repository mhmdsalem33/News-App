package com.salem.apps.presentation.widget


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.salem.apps.R
import com.salem.apps.presentation.ui.main.activity.MainViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchTextField( viewModel: MainViewModel = hiltViewModel()) {

    var textField by remember { mutableStateOf("") }
    val color = colorResource( id = R.color.gray_30 )
    val coroutineScope = rememberCoroutineScope()
    var searchJob by remember { mutableStateOf<Job?>(null) }

    val appFontFamily = FontFamily(
        fonts = listOf(
            Font(
                resId = R.font.omnes_arabic,
                weight = FontWeight.W900,
                style = FontStyle.Normal
            )
        )
    )

    TextField(
        value          =   textField,
        onValueChange  = {
            textField = it
            searchJob?.cancel()
            searchJob = coroutineScope.launch {
                delay(500L)
                viewModel.getBitCoinsNews(it)
            }

        },
        textStyle      =   LocalTextStyle.current.copy(
            textAlign  =   TextAlign.Start,
            fontFamily =   appFontFamily
        ),
        placeholder = {
            Text(text = "Search " , fontFamily = appFontFamily )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = color,
            focusedContainerColor   = color,
            focusedIndicatorColor   = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(15.dp),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "clear icon",
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = "clear icon",
                modifier = Modifier.clickable {
                    Log.e("testText", textField )
                    textField = ""
                }
            )
        }
    )

}