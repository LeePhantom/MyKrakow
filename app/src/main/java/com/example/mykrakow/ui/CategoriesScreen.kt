package com.example.mykrakow.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mykrakow.data.CategoriesDataProvider
import com.example.mykrakow.model.Category
import com.example.mykrakow.ui.theme.MyKrakowTheme
import com.example.mykrakow.R



@Composable
fun CategoriesScreen(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn() {
        items(categories) { category ->
            CategoriesListItem(
                category = category,
                onCategoryClick = onCategoryClick,
                modifier = modifier
            )
        }
    }
}


@Composable
private fun CategoriesListItem(
    category: Category,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onCategoryClick(category) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            CategoryListItemIcon(icon = category.icon)
            Text(
                text = stringResource(category.titleResourceId),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun CategoryListItemIcon(
    icon: ImageVector
) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .size(dimensionResource(R.dimen.categories_icon_size))
            .padding(dimensionResource(R.dimen.padding_medium))
    )
}


//------------------------------------------------------------

@Preview(showBackground = true)
@Composable
fun CategoryListItemPreview() {
    MyKrakowTheme{
        CategoriesListItem(
            category = CategoriesDataProvider.defaultCategory,
            modifier = Modifier.padding(4.dp),
            onCategoryClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesListPreview() {
    MyKrakowTheme {
        CategoriesScreen(
            categories = CategoriesDataProvider.getCategories(),
            onCategoryClick = {},
        )
    }
}