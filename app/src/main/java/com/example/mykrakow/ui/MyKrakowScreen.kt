package com.example.mykrakow.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mykrakow.R
import com.example.mykrakow.ui.theme.MyKrakowTheme


// Enum class used for screen navigation
enum class MyKrakowScreen() {
    CATEGORIES,
    RECOMMENDATIONS,
    DETAILS
}


@Composable
fun MyKrakowApp(
    navController: NavHostController = rememberNavController()
) {
    // Initialize categories ViewModel
    val categoriesViewModel: CategoriesViewModel = viewModel()
    val categoriesUiState by categoriesViewModel.uiState.collectAsState()

    // Create Recommendations ViewModel
    val recommendationsViewModel: RecommendationsViewModel = viewModel()
    val recommendationsUiState by recommendationsViewModel.uiState.collectAsState()

    // Create back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Get name of current screen
    val currentScreen = MyKrakowScreen.valueOf(
        backStackEntry?.destination?.route ?: MyKrakowScreen.CATEGORIES.name
    )

    Scaffold(
        topBar = {
            MyKrakowAppBar(
                currentScreen = currentScreen,
                categoriesUiState = categoriesUiState,
                recommendationsUiState = recommendationsUiState,
                canNavigateUp = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        // Initialize navigation host and create NavGraph
        NavHost(
            navController = navController,
            startDestination = MyKrakowScreen.CATEGORIES.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = MyKrakowScreen.CATEGORIES.name) {

                CategoriesScreen(
                    categories = categoriesUiState.categoriesList,
                    onCategoryClick = { category ->
                        categoriesViewModel.updateCategory(category)
                        recommendationsViewModel.updateRecommendations(category)
                        navController.navigate(MyKrakowScreen.RECOMMENDATIONS.name)
                    }
                )
            }
            composable(route = MyKrakowScreen.RECOMMENDATIONS.name){
                RecommendationsScreen(
                    recommendations = recommendationsUiState.currentRecommendationsList,
                    onRecommendationClick = { recommendation ->
                        recommendationsViewModel.updateDetails(recommendation)
                        navController.navigate(MyKrakowScreen.DETAILS.name)
                    }
                )
            }
            composable(route = MyKrakowScreen.DETAILS.name) {
                DetailsScreen(
                    recommendation = recommendationsUiState.currentDetails
                )

            }
        }
    }
}
//categoriesViewModel.updateCurrentCategory(it)
// TODO: dynamic top bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyKrakowAppBar(
    currentScreen: MyKrakowScreen,
    categoriesUiState: CategoriesUiState,
    recommendationsUiState: RecommendationsUiState,
    canNavigateUp: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentAppBarTitleId = when (currentScreen) {
        MyKrakowScreen.CATEGORIES -> R.string.app_name
        MyKrakowScreen.RECOMMENDATIONS -> categoriesUiState.currentCategory.titleResourceId
        else -> recommendationsUiState.currentDetails.titleResourceId
    }
    TopAppBar(
        title = { Text(
            text = stringResource(currentAppBarTitleId),
            fontWeight = FontWeight.Bold
        ) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateUp) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_arrow_description)
                    )
                }
            }
        }
    )
}


@Preview
@Composable
fun MyKrakowAppDarkPreview() {
    MyKrakowTheme(darkTheme = true) {
        MyKrakowApp()
    }
}