package com.example.mykrakow.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.mykrakow.R
import com.example.mykrakow.ui.theme.MyKrakowTheme
import com.example.mykrakow.ui.utils.MyKrakowContentType


// Enum class used for screen navigation
enum class MyKrakowScreen() {
    CATEGORIES,
    RECOMMENDATIONS,
    DETAILS
}


@Composable
fun MyKrakowApp(
    navController: NavHostController = rememberNavController(),
    windowSize: WindowWidthSizeClass,
    modifier: Modifier
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

    // Determine which UI layout to display
    val contentType: MyKrakowContentType
    if (windowSize == WindowWidthSizeClass.Expanded) {
        contentType = MyKrakowContentType.COMBINED_SCREENS
    } else {
        contentType = MyKrakowContentType.SINGLE_SCREEN
    }


    Scaffold(
        topBar = {
            MyKrakowAppBar(
                currentScreen = currentScreen,
                categoriesUiState = categoriesUiState,
                recommendationsUiState = recommendationsUiState,
                canNavigateUp = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                onLogoClick = { navController.navigate(MyKrakowScreen.CATEGORIES.name) },
                contentType = contentType
            )
        }
    ) { innerPadding ->
        // Initialize navigation host and create NavGraph
        NavHost(
            navController = navController,
            startDestination = MyKrakowScreen.CATEGORIES.name,
            modifier = modifier.padding(innerPadding)
        ) {
            if (contentType == MyKrakowContentType.SINGLE_SCREEN) {
                composable(route = MyKrakowScreen.CATEGORIES.name) {
                    CategoriesScreen(
                        categories = categoriesUiState.categoriesList,
                        onCategoryClick = { category ->
                            categoriesViewModel.updateCategory(category)
                            recommendationsViewModel.updateRecommendations(category)
                            navController.navigate(MyKrakowScreen.RECOMMENDATIONS.name)
                        },
                        modifier = modifier
                    )
                }
                composable(route = MyKrakowScreen.RECOMMENDATIONS.name){
                    RecommendationsScreen(
                        recommendations = recommendationsUiState.currentRecommendationsList,
                        onRecommendationClick = { recommendation ->
                            recommendationsViewModel.updateDetails(recommendation)
                            navController.navigate(MyKrakowScreen.DETAILS.name)
                        },
                        modifier = modifier
                    )
                }
                composable(route = MyKrakowScreen.DETAILS.name) {
                    DetailsScreen(
                        recommendation = recommendationsUiState.currentDetails,
                        modifier = modifier
                    )
                }
            } else {
                composable(route = MyKrakowScreen.CATEGORIES.name) {
                    CategoriesAndRecommendationsScreen(
                        categories = categoriesUiState.categoriesList,
                        onCategoryClick = {
                            categoriesViewModel.updateCategory(it)
                            recommendationsViewModel.updateRecommendations(it)
                        },
                        recommendations = recommendationsUiState.currentRecommendationsList,
                        onRecommendationClick = {
                            recommendationsViewModel.updateRecommendations(
                                categoriesUiState.currentCategory)
                            navController.navigate(MyKrakowScreen.RECOMMENDATIONS.name)
                        },
                        modifier = modifier
                    )
                }
                composable(route = MyKrakowScreen.RECOMMENDATIONS.name) {
                    RecommendationsAndDetailsScreen(
                        recommendations = recommendationsUiState.currentRecommendationsList,
                        onRecommendationClick = {
                            recommendationsViewModel.updateDetails(it)
                        },
                        recommendation = recommendationsUiState.currentDetails,
                    )
                }
                composable(route = MyKrakowScreen.DETAILS.name) {
                    RecommendationsAndDetailsScreen(
                        recommendations = recommendationsUiState.currentRecommendationsList,
                        onRecommendationClick = {
                            recommendationsViewModel.updateDetails(it)
                        },
                        recommendation = recommendationsUiState.currentDetails,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyKrakowAppBar(
    currentScreen: MyKrakowScreen,
    categoriesUiState: CategoriesUiState,
    recommendationsUiState: RecommendationsUiState,
    canNavigateUp: Boolean,
    navigateUp: () -> Unit,
    onLogoClick: () -> Unit,
    contentType: MyKrakowContentType,
    modifier: Modifier = Modifier
) {
    data class AppBarStyle(
        val titleId: Int,
        val fontStyle: TextStyle,
        val appLogoSize: Int
        )

    val (titleId, fontStyle, appLogoSize) = when (currentScreen) {
        MyKrakowScreen.CATEGORIES -> AppBarStyle(
            R.string.app_name,
            MaterialTheme.typography.displaySmall,
            R.dimen.app_logo_size_large
        )
        MyKrakowScreen.RECOMMENDATIONS -> AppBarStyle(
            categoriesUiState.currentCategory.titleResourceId,
            MaterialTheme.typography.displaySmall,
            R.dimen.app_logo_size_medium
        )
        else -> AppBarStyle(
                recommendationsUiState.currentDetails.titleResourceId,
                MaterialTheme.typography.displaySmall,
                R.dimen.app_logo_size_medium
            )
    }

    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.clickable { onLogoClick() }) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(R.raw.my_krakow_logo)
                            .decoderFactory(SvgDecoder.Factory())
                            .size(200, 300)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier.size(dimensionResource(appLogoSize)),
                    )
                }
                Text(
                    text = stringResource(titleId),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = fontStyle,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(start = dimensionResource(R.dimen.padding_large))
                        .basicMarquee(
                            iterations = Int.MAX_VALUE,
                            initialDelayMillis = 1000,
                            velocity = 50.dp
                        )
                )
            } },
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
fun MyKrakowAppPreview() {
    MyKrakowTheme(darkTheme = false) {
        MyKrakowApp(
            windowSize = WindowWidthSizeClass.Medium,
            modifier = Modifier.fillMaxSize()
        )
    }
}