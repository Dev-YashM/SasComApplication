package com.example.sascomapplication.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector? = null // ✅ Made nullable with default null
) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Login : Screen("login", "Login")
    object Register : Screen("register", "Register")
    object Search : Screen("search", "Search", Icons.Default.Search)
    object Cart : Screen("cart", "Cart", Icons.Default.ShoppingCart)
    object Wishlist : Screen("wishlist", "Wishlist", Icons.Default.Favorite)
    object Checkout : Screen("checkout", "Checkout", Icons.Default.Check)
    object Splash : Screen("splash", "Splash", Icons.Default.Info)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
    object OrderPlaced : Screen("order_placed", "Order Placed", Icons.Default.CheckCircle)

    // 🆕 Product Detail Screen (icon is null)
    object ProductDetail : Screen("product_detail", "ProductDetail") {
        fun withArgs(productId: Int): String {
            return "$route/$productId"
        }
    }

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
