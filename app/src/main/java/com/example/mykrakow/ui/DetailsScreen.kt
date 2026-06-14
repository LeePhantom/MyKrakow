package com.example.mykrakow.ui

import android.media.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mykrakow.R
import com.example.mykrakow.data.RecommendationsDataProvider
import com.example.mykrakow.model.Recommendation
import com.example.mykrakow.ui.theme.MyKrakowTheme

@Composable
fun DetailsScreen(recommendation: Recommendation) {
    Column(
        modifier = Modifier.padding(dimensionResource(R.dimen.padding_large))
    ) {
        DetailsItemImage(recommendation.imageResourceId)
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        DetailsItemDescription(recommendation.descriptionResourceId)
    }
}

@Composable
fun DetailsItemImage(imageId: Int) {
    Image(
        painter = painterResource(imageId),
        contentDescription = null,
        modifier = Modifier
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
    descriptionId: Int
) {
    Text(
        text = stringResource(descriptionId),
        style = MaterialTheme.typography.bodyLarge
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
        ))
    }
}

