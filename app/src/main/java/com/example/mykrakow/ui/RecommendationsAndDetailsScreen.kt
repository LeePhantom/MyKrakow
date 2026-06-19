package com.example.mykrakow.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.mykrakow.model.Recommendation
import com.example.mykrakow.R

@Composable
fun RecommendationsAndDetailsScreen(
    recommendations: List<Recommendation>,
    onRecommendationClick: (Recommendation) -> Unit,
    recommendation: Recommendation,
    modifier: Modifier = Modifier
) {
    Row() {
        RecommendationsScreen(
            recommendations = recommendations,
            onRecommendationClick = onRecommendationClick,
            modifier = modifier.width(dimensionResource(R.dimen.large_screen_card_width))
        )
        DetailsScreen(
            recommendation = recommendation,
            modifier = modifier
                .widthIn(max = dimensionResource(R.dimen.details_column_width))
        )
    }
}