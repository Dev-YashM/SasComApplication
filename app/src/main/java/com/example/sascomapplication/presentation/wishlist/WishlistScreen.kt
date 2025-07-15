package com.example.sascomapplication.presentation.wishlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.sascomapplication.domain.model.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(
    viewModel: WishlistViewModel = hiltViewModel()
) {
    val wishlist by viewModel.wishlist.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Your Wishlist") })
        }
    ) { padding ->
        if (wishlist.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Your wishlist is empty ☹️")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(wishlist) { product ->
                    WishlistItem(product = product) {
                        viewModel.removeFromWishlist(product)
                    }
                }
            }
        }
    }
}

@Composable
fun WishlistItem(product: Product, onRemove: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = product.thumbnail),
                contentDescription = product.title,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = product.title, style = MaterialTheme.typography.titleMedium)
                Text(text = "₹${product.price}", style = MaterialTheme.typography.bodyMedium)
            }

            IconButton(onClick = onRemove) {
                Icon(
                    painter = rememberAsyncImagePainter(model = "https://cdn-icons-png.flaticon.com/512/1214/1214428.png"), // Or use painterResource if you have a local icon
                    contentDescription = "Remove"
                )
            }
        }
    }
}
