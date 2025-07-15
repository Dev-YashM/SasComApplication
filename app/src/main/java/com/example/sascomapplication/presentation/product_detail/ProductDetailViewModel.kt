package com.example.sascomapplication.presentation.product_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sascomapplication.domain.model.Product
import com.example.sascomapplication.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _productState = MutableStateFlow<Product?>(null)
    val productState = _productState.asStateFlow()

    fun loadProductById(id: Int) {
        viewModelScope.launch {
            val products = repository.getAllProducts()
            _productState.value = products.find { it.id == id }
        }
    }
}
