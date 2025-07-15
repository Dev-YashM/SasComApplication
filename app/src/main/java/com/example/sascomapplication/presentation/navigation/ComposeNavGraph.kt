package com.example.sascomapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sascomapplication.presentation.auth.LoginScreen
import com.example.sascomapplication.presentation.auth.RegisterScreen
import com.example.sascomapplication.presentation.cart.CartScreen
import com.example.sascomapplication.presentation.cart.CartViewModel
import com.example.sascomapplication.presentation.checkout.CheckoutScreen
import com.example.sascomapplication.presentation.home.HomeScreen
import com.example.sascomapplication.presentation.home.HomeViewModel
import com.example.sascomapplication.presentation.product_detail.ProductDetailScreen
import com.example.sascomapplication.presentation.profile.ProfileScreen
import com.example.sascomapplication.presentation.search.SearchScreen
import com.example.sascomapplication.presentation.search.SearchViewModel
import com.example.sascomapplication.presentation.splash.SplashScreen
import com.example.sascomapplication.presentation.wishlist.WishlistScreen
import com.example.sascomapplication.presentation.wishlist.WishlistViewModel
import com.google.gson.Gson

@Composable
fun ComposeNavGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    searchViewModel: SearchViewModel,
    cartViewModel: CartViewModel,
    wishlistViewModel: WishlistViewModel,
) {
    val sharedCartViewModel: CartViewModel = hiltViewModel()
    val sharedWishlistViewModel: WishlistViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(Screen.Login.route) {
            LoginScreen(
                navController,
                onLoginSuccess = TODO()
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                navController,
                onRegisterSuccess = TODO()
            )
        }

        composable(route = Screen.Home.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val wishlistViewModel: WishlistViewModel = hiltViewModel()

            HomeScreen(
                viewModel = homeViewModel,
                onProductClick = { product ->
                    // ✅ This line: use `product.id` from passed product
                    navController.navigate(Screen.ProductDetail.withArgs(product))
                }
            )
        }


        composable(Screen.Search.route) {
            SearchScreen(
                viewModel = searchViewModel,
                onProductClick = { product ->
                    navController.navigate(Screen.ProductDetail.withArgs(product.id))
                }
            )
        }

        composable(Screen.Cart.route) {
            CartScreen(
                viewModel = cartViewModel,
                onCheckoutClick = {
                    navController.navigate(Screen.Checkout.route)
                }
            )
        }

        composable(Screen.Checkout.route) {
            CheckoutScreen(
                cartViewModel = sharedCartViewModel,
                onOrderPlaced = {
                    navController.navigate(Screen.OrderPlaced.route)
                }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }

        composable(Screen.Wishlist.route) {
            WishlistScreen(viewModel = sharedWishlistViewModel)
        }

        composable(
            route = Screen.ProductDetail.route + "/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: -1

            // Inject shared ViewModels
            val cartViewModel: CartViewModel = hiltViewModel()
            val wishlistViewModel: WishlistViewModel = hiltViewModel()

            ProductDetailScreen(
                productId = productId,
                cartViewModel = cartViewModel,
                wishlistViewModel = wishlistViewModel
            )
        }

        composable(Screen.OrderPlaced.route) {
            // TODO: OrderPlacedScreen()
        }
    }
}
