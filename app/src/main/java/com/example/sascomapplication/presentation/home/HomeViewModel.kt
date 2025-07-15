package com.example.sascomapplication.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sascomapplication.domain.model.Product
import com.example.sascomapplication.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

// ✅ Add this at the top (above your ViewModel)
data class HomeUiState(
    val categories: List<String> = emptyList(),
    val products: List<Product> = emptyList(),
    val filteredProducts: List<Product> = emptyList(),
    val selectedCategory: String = ""
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val products = repository.getAllProducts()
            val categories = products.map { it.category }.distinct()

            _state.value = _state.value.copy(
                products = products,
                filteredProducts = products,
                categories = categories
            )
        }
    }

    fun onCategorySelected(category: String) {
        val filtered = _state.value.products.filter { it.category == category }
        _state.value = _state.value.copy(
            selectedCategory = category,
            filteredProducts = filtered
        )
    }
}
