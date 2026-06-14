package com.example.mykrakow.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mykrakow.data.RecommendationsDataProvider
import com.example.mykrakow.model.Recommendation
import com.example.mykrakow.ui.theme.MyKrakowTheme
import com.example.mykrakow.R



@Composable
fun RecommendationsScreen(
    recommendations: List<Recommendation>,
    onRecommendationClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn() {
        items(recommendations) { recommendation ->
            RecommendationsListItem(
                recommendation = recommendation,
                onItemClick = onRecommendationClick,
                modifier = modifier
            )
        }
    }
}


@Composable
private fun RecommendationsListItem(
    recommendation: Recommendation,
    onItemClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = Modifier
            .padding(bottom = dimensionResource(R.dimen.padding_medium))
            .height(dimensionResource(R.dimen.card_height)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(recommendation) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            RecommendationListItemLogo(logoId = recommendation.logoImageResourceId)
            Text(
                text = stringResource(recommendation.titleResourceId),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun RecommendationListItemLogo(
    logoId: Int
) {
    Image(
        painter = painterResource(logoId),
        contentDescription = null,
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .border(BorderStroke(
                dimensionResource(R.dimen.logo_image_border_width),
                    MaterialTheme.colorScheme.onPrimaryContainer),
                CircleShape
            )
            .clip(CircleShape)

    )
}


@Preview(showBackground = true)
@Composable
fun RecommendationListItemPreview() {
    MyKrakowTheme{
        RecommendationsListItem(
            recommendation = RecommendationsDataProvider.getPlaces()[0],
            modifier = Modifier.padding(4.dp),
            onItemClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationsListPreview() {
    MyKrakowTheme {
        RecommendationsScreen(
            recommendations = RecommendationsDataProvider.getRamens(),
            onRecommendationClick = {}
        )
    }
}