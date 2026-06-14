package com.example.mykrakow.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Castle
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.RamenDining
import com.example.mykrakow.R
import com.example.mykrakow.model.Category

object CategoriesDataProvider {

    val defaultCategory = getCategories()[0]
    fun getCategories(): List<Category> {
        return listOf(
            Category(
                titleResourceId = R.string.category_1,
                icon = Icons.Filled.Castle,
            ),
            Category(
                titleResourceId = R.string.category_2,
                icon = Icons.Filled.Park,
            ),
            Category(
                titleResourceId = R.string.category_3,
                icon = Icons.Filled.RamenDining,
            ),
            Category(
                titleResourceId = R.string.category_4,
                icon = Icons.Filled.LocalCafe,
            ),
        )
    }
}