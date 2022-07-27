package com.pdp.declarative.activity.screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
fun DetailsScreen(
    navController: NavController,
    show_id: String,
    show_name: String,
    show_type: String
) {
    val viewModel = hiltViewModel<DetailsViewModel>()
    viewModel.apiTVShowDetails(show_id.toInt())

    DetailsScreenContent(viewModel, show_id, show_name, show_type)
}

@Composable
fun DetailsScreenContent(
    viewModel: DetailsViewModel,
    show_id: String,
    show_name: String,
    show_type: String
) {
    val isLoading by viewModel.isLoading.observeAsState(false)
    val tvShowDetails by viewModel.tvShowDetails.observeAsState()

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
                Box {
                    Box(
                        modifier = Modifier.height(500.dp)
                    ) {
                        if (tvShowDetails != null) {
                            val img_url =
                                "https://static.episodate.com/images/tv-show/full/$show_id.jpg"
                            GlideImage(
                                imageModel = img_url,
                                // Crop, Fit, Inside, FillHeight, FillWidth, None
                                contentScale = ContentScale.Crop,
                            )
                        }

                    }
                    Box(
                        modifier = Modifier.height(500.dp),
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .background(color = Color.Black.copy(alpha = 0.6f),),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Spacer(modifier = Modifier.width(15.dp))
                                Column {
                                    Text(show_name, color = Color.White)
                                    Text(show_type, color = Color.Yellow)
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}