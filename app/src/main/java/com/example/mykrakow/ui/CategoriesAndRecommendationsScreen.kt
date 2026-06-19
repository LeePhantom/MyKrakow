package com.example.mykrakow.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mykrakow.R
import com.example.mykrakow.data.CategoriesDataProvider
import com.example.mykrakow.data.RecommendationsDataProvider
import com.example.mykrakow.model.Category
import com.example.mykrakow.model.Recommendation
import com.example.mykrakow.ui.theme.MyKrakowTheme

@Composable
fun CategoriesAndRecommendationsScreen(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    recommendations: List<Recommendation>,
    onRecommendationClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        CategoriesScreen(
            categories = categories,
            onCategoryClick = onCategoryClick,
            modifier = Modifier.width(dimensionResource(R.dimen.large_screen_card_width))
        )
        RecommendationsScreen(
            recommendations = recommendations,
            onRecommendationClick = onRecommendationClick,
            modifier = modifier
        )

    }
}

@Preview(device = Devices.TABLET)
@Composable
fun CategoriesAndRecommendationsScreenPreview() {
    MyKrakowTheme() {
        CategoriesAndRecommendationsScreen(
            categories = CategoriesDataProvider.getCategories(),
            onCategoryClick = {},
            recommendations = RecommendationsDataProvider.getRamens(),
            onRecommendationClick = {},
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_large))
        )
    }
}