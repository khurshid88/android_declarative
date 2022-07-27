package com.pdp.declarative.activity.screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pdp.declarative.item.itemTVShow
import com.pdp.declarative.viewmodel.DetailsViewModel
import com.pdp.declarative.viewmodel.MainViewModel
import com.pdp.imperative.model.TVShow
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailsScreen(navController: NavController, show_id:String) {
    val viewModel = hiltViewModel<DetailsViewModel>()
    val isLoading by viewModel.isLoading.observeAsState(false)
    viewModel.apiTVShowDetails(show_id.toInt())

    DetailsScreenContent(isLoading)
}

@Composable
fun DetailsScreenContent(isLoading: Boolean) {

    Scaffold(
        backgroundColor = Color.Black,
    ) {
        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = Color.White
                )
            }
        } else {
            Column {
                Box() {
                    GlideImage(
                        imageModel = "https://static.episodate.com/images/tv-show/thumbnail/29671.png",
                        // Crop, Fit, Inside, FillHeight, FillWidth, None
                        contentScale = ContentScale.FillBounds,
                    )
                }
            }
        }
    }
}