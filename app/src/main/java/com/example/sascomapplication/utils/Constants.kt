package com.example.sascomapplication.utils

object Constants {

    // Base URL for the API
    const val BASE_URL = "https://dummyjson.com/"

    // Navigation Routes
    const val SPLASH_SCREEN = "splash_screen"
    const val HOME_SCREEN = "home_screen"
    const val CART_SCREEN = "cart_screen"
    const val WISHLIST_SCREEN = "wishlist_screen"
    const val SEARCH_SCREEN = "search_screen"
    const val CHECKOUT_SCREEN = "checkout_screen"
    const val PRODUCT_DETAIL_SCREEN = "product_detail_screen"

    // Delay Time
    const val SPLASH_DELAY = 2000L // 2 seconds

    // API Endpoints
    const val PRODUCTS_ENDPOINT = "products"
    const val CATEGORIES_ENDPOINT = "products/categories"
    const val PRODUCTS_BY_CATEGORY_ENDPOINT = "products/category/"

    // UI-related
    const val DEFAULT_PADDING = 16

    // Misc
    const val NETWORK_TIMEOUT = 30L // seconds
}
