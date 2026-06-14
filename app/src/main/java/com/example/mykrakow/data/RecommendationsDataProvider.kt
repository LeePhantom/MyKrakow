package com.example.mykrakow.data

import androidx.compose.ui.res.stringResource
import com.example.mykrakow.R
import com.example.mykrakow.model.Recommendation

object RecommendationsDataProvider {

    // Combine all the recommendations
    fun getAllRecommendations(): List<Recommendation> {
        return getPlaces() + getParks() + getRamens() + getCoffees()
    }

    // Filter the recommendations by category
    fun getRecommendationsByCategory(categoryRes: Int): List<Recommendation> {
        return getAllRecommendations().filter { it.category == categoryRes }
    }

    fun getPlaces(): List<Recommendation> {
        return listOf(
            Recommendation(
                titleResourceId = R.string.place_1_title,
                descriptionResourceId = R.string.place_1_desc,
                imageResourceId= R.drawable.place_1,
                logoImageResourceId = R.drawable.place_1_logo,
                category = R.string.category_1
            ),
            Recommendation(
                titleResourceId = R.string.place_2_title,
                descriptionResourceId = R.string.place_2_desc,
                imageResourceId= R.drawable.place_2,
                logoImageResourceId = R.drawable.place_2_logo,
                category = R.string.category_1
            ),
            Recommendation(
                titleResourceId = R.string.place_3_title,
                descriptionResourceId = R.string.place_3_desc,
                imageResourceId= R.drawable.place_3,
                logoImageResourceId = R.drawable.place_3_logo,
                category = R.string.category_1
            ),
            Recommendation(
                titleResourceId = R.string.place_4_title,
                descriptionResourceId = R.string.place_4_desc,
                imageResourceId= R.drawable.place_4,
                logoImageResourceId = R.drawable.place_4_logo,
                category = R.string.category_1
            ),
            Recommendation(
                titleResourceId = R.string.place_5_title,
                descriptionResourceId = R.string.place_5_desc,
                imageResourceId= R.drawable.place_5,
                logoImageResourceId = R.drawable.place_5_logo,
                category = R.string.category_1
            )
        )
    }
    fun getParks(): List<Recommendation> {
        return listOf(
            Recommendation(
                titleResourceId = R.string.park_1_title,
                descriptionResourceId = R.string.park_1_desc,
                imageResourceId= R.drawable.park_1,
                logoImageResourceId = R.drawable.park_logo,
                category = R.string.category_2
            ),
            Recommendation(
                titleResourceId = R.string.park_2_title,
                descriptionResourceId = R.string.park_2_desc,
                imageResourceId= R.drawable.park_2,
                logoImageResourceId = R.drawable.park_logo,
                category = R.string.category_2
            ),
            Recommendation(
                titleResourceId = R.string.park_3_title,
                descriptionResourceId = R.string.park_3_desc,
                imageResourceId= R.drawable.park_3,
                logoImageResourceId = R.drawable.park_logo,
                category = R.string.category_2
            ),
            Recommendation(
                titleResourceId = R.string.park_4_title,
                descriptionResourceId = R.string.park_4_desc,
                imageResourceId= R.drawable.park_4,
                logoImageResourceId = R.drawable.park_logo,
                category = R.string.category_2
            ),
            Recommendation(
                titleResourceId = R.string.park_5_title,
                descriptionResourceId = R.string.park_5_desc,
                imageResourceId= R.drawable.park_5,
                logoImageResourceId = R.drawable.park_logo,
                category = R.string.category_2
            ),
        )
    }
    fun getRamens(): List<Recommendation> {
        return listOf(
            Recommendation(
                titleResourceId = R.string.ramen_1_title,
                descriptionResourceId = R.string.ramen_1_desc,
                imageResourceId= R.drawable.ramen_1,
                logoImageResourceId = R.drawable.ramen_1_logo,
                category = R.string.category_3
            ),
            Recommendation(
                titleResourceId = R.string.ramen_2_title,
                descriptionResourceId = R.string.ramen_2_desc,
                imageResourceId= R.drawable.ramen_2,
                logoImageResourceId = R.drawable.ramen_2_logo,
                category = R.string.category_3
            ),
            Recommendation(
                titleResourceId = R.string.ramen_3_title,
                descriptionResourceId = R.string.ramen_3_desc,
                imageResourceId= R.drawable.ramen_3,
                logoImageResourceId = R.drawable.ramen_3_logo,
                category = R.string.category_3
            ),
            Recommendation(
                titleResourceId = R.string.ramen_4_title,
                descriptionResourceId = R.string.ramen_4_desc,
                imageResourceId= R.drawable.ramen_4,
                logoImageResourceId = R.drawable.ramen_4_logo,
                category = R.string.category_3
            ),
            Recommendation(
                titleResourceId = R.string.ramen_5_title,
                descriptionResourceId = R.string.ramen_5_desc,
                imageResourceId= R.drawable.ramen_5,
                logoImageResourceId = R.drawable.ramen_5_logo,
                category = R.string.category_3
            ),
        )
    }
    fun getCoffees(): List<Recommendation> {
        return listOf(
            Recommendation(
                titleResourceId = R.string.coffee_1_title,
                descriptionResourceId = R.string.coffee_1_desc,
                imageResourceId= R.drawable.coffee_1,
                logoImageResourceId = R.drawable.coffee_1_logo,
                category = R.string.category_4
            ),
            Recommendation(
                titleResourceId = R.string.coffee_2_title,
                descriptionResourceId = R.string.coffee_2_desc,
                imageResourceId= R.drawable.coffee_2,
                logoImageResourceId = R.drawable.coffee_2_logo,
                category = R.string.category_4
            ),
            Recommendation(
                titleResourceId = R.string.coffee_3_title,
                descriptionResourceId = R.string.coffee_3_desc,
                imageResourceId= R.drawable.coffee_3,
                logoImageResourceId = R.drawable.coffee_3_logo,
                category = R.string.category_4
            ),
            Recommendation(
                titleResourceId = R.string.coffee_4_title,
                descriptionResourceId = R.string.coffee_4_desc,
                imageResourceId= R.drawable.coffee_4,
                logoImageResourceId = R.drawable.coffee_4_logo,
                category = R.string.category_4
            ),
            Recommendation(
                titleResourceId = R.string.coffee_5_title,
                descriptionResourceId = R.string.coffee_5_desc,
                imageResourceId= R.drawable.coffee_5,
                logoImageResourceId = R.drawable.coffee_5_logo,
                category = R.string.category_4
            ),
        )
    }
}