package com.example.sascomapplication.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sascomapplication.domain.model.Product
import com.example.sascomapplication.domain.use_case.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _searchResults = MutableStateFlow<List<Product>>(emptyList())
    val searchResults: StateFlow<List<Product>> = _searchResults

    private var allProducts: List<Product> = emptyList()

    init {
        viewModelScope.launch {
            allProducts = getProductsUseCase.getAll() // ✅ Correct usage
            _searchResults.value = allProducts
        }

        viewModelScope.launch {
            _query
                .debounce(300)
                .collect { searchText ->
                    _searchResults.value = if (searchText.isBlank()) {
                        allProducts
                    } else {
                        allProducts.filter {
                            it.title.contains(searchText, ignoreCase = true)
                        }
                    }
                }
        }
    }

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }
}
