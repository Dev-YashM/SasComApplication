package com.example.sascomapplication.presentation.wishlist

import androidx.lifecycle.ViewModel
import com.example.sascomapplication.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx. coroutines. flow. StateFlow
import javax.inject.Inject
@HiltViewModel
class WishlistViewModel @Inject constructor() : ViewModel() {

    private val _wishlist = MutableStateFlow<List<Product>>(emptyList())
    val wishlist: StateFlow<List<Product>> = _wishlist

    fun addToWishlist(product: Product) {
        if (!_wishlist.value.contains(product)) {
            _wishlist.value = _wishlist.value + product
        }
    }

    fun removeFromWishlist(product: Product) {
        _wishlist.value = _wishlist.value - product
    }
}
