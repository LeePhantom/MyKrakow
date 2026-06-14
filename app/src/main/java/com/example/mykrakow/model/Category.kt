package com.example.mykrakow.model

import android.graphics.drawable.Icon
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class Category(
    @StringRes val titleResourceId: Int,
    val icon: ImageVector
)
