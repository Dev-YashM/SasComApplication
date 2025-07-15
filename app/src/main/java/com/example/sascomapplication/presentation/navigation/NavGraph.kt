package com.example.sascomapplication.presentation.navigation

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sascomapplication.presentation.product_detail.ProductDetailScreen
import com.example.sascomapplication.presentation.home.HomeScreen
import com.example.sascomapplication.presentation.search.SearchViewModel
import com.example.sascomapplication.presentation.search.SearchScreen
import com.example.sascomapplication.presentation.cart.CartViewModel
import com.example.sascomapplication.presentation.cart.CartScreen
import com.example.sascomapplication.presentation.checkout.CheckoutScreen
import com.example.sascomapplication.presentation.wishlist.WishlistViewModel
import com.example.sascomapplication.presentation.wishlist.WishlistScreen
import com.example.sascomapplication.presentation.splash.SplashScreen
import androidx.navigation.navArgument
import com.example.sascomapplication.presentation.order.OrderPlacedScreen
import androidx.navigation.NavType

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    // ✅ Shared ViewModel used in multiple destinations
    val sharedCartViewModel: CartViewModel = hiltViewModel()
    val sharedWishlistViewModel: WishlistViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = hiltViewModel(),
                onProductClick = { product ->
                    navController.navigate("product_detail/${product.id}")
                }
            )
        }

        composable(Screen.Search.route) {
            val viewModel: SearchViewModel = hiltViewModel()
            SearchScreen(
                viewModel = viewModel,
                onProductClick = { product ->
                    navController.navigate("product_detail/${product.id}")
                }
            )
        }

        composable(Screen.Cart.route) {
            CartScreen(
                viewModel = sharedCartViewModel,
                onCheckoutClick = {
                    navController.navigate(Screen.Checkout.route)
                }
            )
        }

        composable(Screen.Checkout.route) {
            val cartViewModel: CartViewModel = hiltViewModel()
            CheckoutScreen(
                cartViewModel = sharedCartViewModel,
                onOrderPlaced = {
                    navController.navigate(Screen.OrderPlaced.route)
                }
            )
        }


        composable(Screen.Wishlist.route) {
            WishlistScreen(viewModel = sharedWishlistViewModel)
        }

        composable(
            route = "product_detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailScreen(
                productId = productId,
                cartViewModel = sharedCartViewModel, // ✅ Fix: shared CartViewMode
                wishlistViewModel = sharedWishlistViewModel
            )
        }

        composable(Screen.OrderPlaced.route) {
            OrderPlacedScreen(navController = navController)
        }

    }
}