package com.example.mykrakow.model

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.mykrakow.R

data class Recommendation(
    @StringRes val titleResourceId: Int,
    @StringRes val descriptionResourceId: Int = R.string.no_description,
    @DrawableRes val imageResourceId: Int,
    @DrawableRes val logoImageResourceId: Int,
    @StringRes val category: Int
)