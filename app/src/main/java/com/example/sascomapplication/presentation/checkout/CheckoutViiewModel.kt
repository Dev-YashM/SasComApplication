//package com.example.sascomapplication.presentation.checkout
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//data class CheckoutState(
//    val items: List<CartItem> = emptyList(),
//    val totalPrice: Double = 0.0,
//    val isLoading: Boolean = false,
//    val error: String? = null,
//    val orderPlaced: Boolean = false
//)
//
//data class CartItem(
//    val id: Int,
//    val name: String,
//    val price: Double,
//    val quantity: Int
//)
//
//class CheckoutViewModel : ViewModel() {
//
//    private val _state = MutableStateFlow(CheckoutState())
//    val state: StateFlow<CheckoutState> = _state
//
//    init {
//        loadCartItems()
//    }
//
//    private fun loadCartItems() {
//        // Mock Data – You can fetch this from a repository in real implementation
//        val cartItems = listOf(
//            CartItem(1, "Smartphone", 999.99, 1),
//            CartItem(2, "Headphones", 199.99, 2)
//        )
//        val total = cartItems.sumOf { it.price * it.quantity }
//
//        _state.value = _state.value.copy(items = cartItems, totalPrice = total)
//    }
//
//    fun placeOrder() {
//        viewModelScope.launch {
//            _state.value = _state.value.copy(isLoading = true)
//
//            // Simulate order placement delay
//            kotlinx.coroutines.delay(2000)
//
//            _state.value = _state.value.copy(isLoading = false, orderPlaced = true)
//        }
//    }
//}
