package com.example.sascomapplication.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sascomapplication.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {

    private val _cartItems = MutableStateFlow<List<Product>>(emptyList())
    val cartItems: StateFlow<List<Product>> = _cartItems.asStateFlow()

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice: StateFlow<Double> = _totalPrice.asStateFlow()

    init {
        viewModelScope.launch {
            _cartItems.collect { items ->
                _totalPrice.value = items.sumOf { it.price }
            }
        }
    }

    fun addToCart(product: Product) {
        _cartItems.update { current ->
            current + product
        }
    }

    fun removeFromCart(product: Product) {
        _cartItems.update { current ->
            current - product
        }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }
}
