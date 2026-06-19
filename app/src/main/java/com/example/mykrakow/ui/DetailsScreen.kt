package com.example.mykrakow.ui

import android.media.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mykrakow.R
import com.example.mykrakow.data.RecommendationsDataProvider
import com.example.mykrakow.model.Recommendation
import com.example.mykrakow.ui.theme.MyKrakowTheme

@Composable
fun DetailsScreen(
    recommendation: Recommendation,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_large))
            .verticalScroll(rememberScrollState())
    ) {
        DetailsItemImage(imageId = recommendation.imageResourceId)
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        DetailsItemDescription(recommendation.descriptionResourceId)
    }
}

@Composable
fun DetailsItemImage(imageId: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(imageId),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .border(BorderStroke(
                dimensionResource(R.dimen.details_image_border_width),
                MaterialTheme.colorScheme.onPrimaryContainer),
                shape = RoundedCornerShape(dimensionResource(R.dimen.details_image_corner_radius))
                )
            .clip(RoundedCornerShape(dimensionResource(R.dimen.details_image_corner_radius)))
    )
}

@Composable
fun DetailsItemDescription(
    descriptionId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(descriptionId),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier.fillMaxWidth()
    )
}




@Preview(showBackground = true)
@Composable
fun DetailsItemPreview() {
    MyKrakowTheme {
        DetailsScreen(recommendation = Recommendation(
            titleResourceId = R.string.ramen_1_title,
            descriptionResourceId = R.string.ramen_1_desc,
            imageResourceId= R.drawable.ramen_1,
            logoImageResourceId = R.drawable.ramen_1_logo,
            category = R.string.category_3
            ),
            modifier = Modifier
                .widthIn(max = dimensionResource(R.dimen.details_column_width))
        )
    }
}

