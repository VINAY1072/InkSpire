package com.example.inkspire.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.inkspire.Screen
import com.example.inkspire.presentation.screens.CreateNoteScreen
import com.example.inkspire.presentation.screens.HomeScreenComposable
import com.example.inkspire.ui.theme.InkSpireTheme
import com.example.inkspire.viewmodel.CreateViewModel
import com.example.inkspire.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(FlowPreview::class, ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InkSpireTheme {
                val navController = rememberNavController()

                SharedTransitionLayout {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route
                    ) {
                        composable(route = Screen.Home.route) {
                            val homeViewModel: HomeViewModel = hiltViewModel()

                            val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

                            HomeScreenComposable(
                                animatedScope = this,
                                uiState = uiState,
                                onAction = homeViewModel::onAction
                            )

                            LaunchedEffect(Unit) {
                                homeViewModel.openCreateScreen.debounce(400L).collectLatest { id ->
                                    navController.navigate(Screen.CREATE + "/${id}")
                                }
                            }
                        }

                        composable(
                            route = Screen.Create.route,
                            arguments = listOf(navArgument("id") { type = IntType })
                        ) { entry ->

                            val id = entry.arguments?.getInt("id")

                            val viewModel: CreateViewModel =
                                hiltViewModel<CreateViewModel, CreateViewModel.CreateViewModelFactory> { factory ->
                                    factory.create(id)
                                }

                            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                            CreateNoteScreen(
                                animatedScope = this,
                                uiState = uiState.copy(id = id),
                                onAction = viewModel::onAction
                            )

                            LaunchedEffect(Unit) {
                                viewModel.shouldGoBack.debounce(300L).collectLatest { go ->
                                    if (go) {
                                        navController.popBackStack()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}