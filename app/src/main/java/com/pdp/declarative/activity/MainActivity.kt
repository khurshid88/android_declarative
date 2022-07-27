package com.pdp.declarative.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pdp.declarative.activity.screen.DetailsScreen
import com.pdp.declarative.activity.screen.MainScreen
import com.pdp.declarative.activity.screen.MainScreenContent
import com.pdp.declarative.item.itemTVShow
import com.pdp.declarative.ui.theme.Android_declarativeTheme
import com.pdp.declarative.utils.Logger
import com.pdp.declarative.viewmodel.DetailsViewModel
import com.pdp.declarative.viewmodel.MainViewModel
import com.pdp.imperative.model.TVShow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_declarativeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "main"
                    ) {
                        composable(route = "main") {
                            MainScreen(navController)
                        }
                        composable(
                            route = "details/{show_id}/{show_name}/{show_type}",
                            arguments = listOf(
                                navArgument("show_id") {
                                    type = NavType.StringType
                                },
                                navArgument("show_name") {
                                    type = NavType.StringType
                                },
                                navArgument("show_type") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val show_id = it.arguments?.getString("show_id")
                            val show_name = it.arguments?.getString("show_name")
                            val show_type = it.arguments?.getString("show_type")
                            DetailsScreen(navController, show_id!!,show_name!!,show_type!!)
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Android_declarativeTheme {
        MainScreenContent(null, false, listOf())
    }
}