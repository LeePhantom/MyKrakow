package com.example.mykrakow.ui

import androidx.lifecycle.ViewModel
import com.example.mykrakow.data.RecommendationsDataProvider
import com.example.mykrakow.model.Category
import com.example.mykrakow.model.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RecommendationsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        RecommendationsUiState(
            currentRecommendationsList = RecommendationsDataProvider.getPlaces(),
            currentDetails = RecommendationsDataProvider.getPlaces()[0]
        )
    )
    // Creates immutable state flow, which can be collected by UI
    val uiState: StateFlow<RecommendationsUiState> = _uiState

    // updates current recommendations list based on clicked category
    fun updateRecommendations(selectedCategory: Category) {
        val filteredRecommendations = RecommendationsDataProvider.getRecommendationsByCategory(
            selectedCategory.titleResourceId)

        _uiState.update {
            it.copy(
                currentRecommendationsList = filteredRecommendations,
                currentDetails = filteredRecommendations[0]
            )
        }
    }
    // Updates shown displayed recommendation details
    fun updateDetails(selectedRecommendation: Recommendation) {
        _uiState.update {
            it.copy(currentDetails = selectedRecommendation)
        }
    }
}

data class RecommendationsUiState(
    val currentRecommendationsList: List<Recommendation> = emptyList(),
    val currentDetails: Recommendation,
)



// RecommendationsDataProvider.getPlaces()