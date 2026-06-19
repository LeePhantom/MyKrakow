package com.example.mykrakow.ui

import androidx.lifecycle.ViewModel
import com.example.mykrakow.data.CategoriesDataProvider
import com.example.mykrakow.data.RecommendationsDataProvider
import com.example.mykrakow.model.Category
import com.example.mykrakow.model.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CategoriesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        CategoriesUiState(
            categoriesList = CategoriesDataProvider.getCategories(),
            currentCategory = CategoriesDataProvider.getCategories().getOrElse(0) {
                CategoriesDataProvider.defaultCategory
            }
        )
    )
    // Creates read-only uiState, which can be collected by UI
    val uiState: StateFlow<CategoriesUiState> = _uiState

    // Updates the current category based on clicked category
    fun updateCategory(selectedCategory: Category) {
        _uiState.update {
            it.copy(
                currentCategory = selectedCategory,
                isShowingCategories = false
            )
        }
    }
    fun updateIsScreenExpanded(isExpanded: Boolean) {
        _uiState.update {
            it.copy(
                isScreenExpanded = isExpanded
            )
        }
    }
}


data class CategoriesUiState(
    val categoriesList: List<Category> = emptyList(),
    val currentCategory: Category = CategoriesDataProvider.defaultCategory,
    val isShowingCategories: Boolean = true,
    val isScreenExpanded: Boolean = false
)