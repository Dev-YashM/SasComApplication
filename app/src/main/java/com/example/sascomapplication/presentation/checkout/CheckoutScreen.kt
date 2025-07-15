package com.example.sascomapplication.presentation.checkout


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx. hilt. navigation. compose. hiltViewModel
import com.example.sascomapplication.presentation.cart.CartViewModel
import kotlinx. coroutines. launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    cartViewModel: CartViewModel = hiltViewModel(),
    onOrderPlaced: () -> Unit
) {
    val cartItems by cartViewModel.cartItems.collectAsState()
    val totalPrice by cartViewModel.totalPrice.collectAsState()

    var orderPlaced by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    // ✅ Correct usage of LaunchedEffect
    if (orderPlaced) {
        LaunchedEffect(true) {
            onOrderPlaced()
            cartViewModel.clearCart()
        }
    }



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Checkout") }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text("Your Items", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))

                    if (cartItems.isEmpty()) {
                        Text("Your cart is empty.")
                    } else {
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(cartItems) { item ->
                                CheckoutItem(name = item.title, price = item.price)
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Total: ₹${"%.2f".format(totalPrice)}", style = MaterialTheme.typography.titleMedium)

                        Spacer(modifier = Modifier.height(16.dp))

                        val coroutineScope = rememberCoroutineScope()

                        Button(
                            onClick = {
                                isLoading = true
                                coroutineScope.launch {
                                    kotlinx.coroutines.delay(1500)
                                    isLoading = false
                                    orderPlaced = true
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Place Order")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CheckoutItem(
    name: String,
    price: Double
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        Text(text = name, style = MaterialTheme.typography.bodyLarge)
        Text(text = "₹${"%.2f".format(price)}", style = MaterialTheme.typography.bodySmall)
    }
}


