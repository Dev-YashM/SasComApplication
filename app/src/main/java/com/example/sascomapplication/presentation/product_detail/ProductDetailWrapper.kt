package com.example.sascomapplication.presentation.product_detail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import com. example. sascomapplication. presentation. cart. CartViewModel
import com. example. sascomapplication. presentation. wishlist. WishlistViewModel

@Composable
fun ProductDetailWrapper(
    productId: Int,
    navController: NavController? = null
) {
    val sharedCartViewModel: CartViewModel = hiltViewModel()
    val sharedWishlistViewModel: WishlistViewModel = hiltViewModel()
    val viewModel: ProductDetailViewModel = hiltViewModel()
    val product by viewModel.productState.collectAsState()

    LaunchedEffect(productId) {
        viewModel.loadProductById(productId)
    }

    product?.let {
        ProductDetailScreen(productId = productId,
                    cartViewModel = sharedCartViewModel,
            wishlistViewModel = sharedWishlistViewModel) // ✅ Correctly calls content composable
    }
}
