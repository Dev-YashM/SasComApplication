package com.example.sascomapplication.presentation.product_detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.sascomapplication.domain.model.Product
import com.example.sascomapplication.presentation.cart.CartViewModel
import com. example. sascomapplication. presentation. wishlist. WishlistViewModel

@Composable
fun ProductDetailScreen(
    productId: Int,
    cartViewModel: CartViewModel,
    wishlistViewModel: WishlistViewModel,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val product by viewModel.product.collectAsState()
    // 🔁 Load product when screen appears
    LaunchedEffect(productId) {
        viewModel.loadProductById(productId)
    }

    product?.let { product ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // 🖼 Product Image
            Image(
                painter = rememberAsyncImagePainter(model = product.thumbnail),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 🏷️ Product Title
            Text(
                text = product.title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 💰 Price & ⭐ Rating
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "₹${product.price}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "⭐ ${product.rating}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "-${product.discountPercentage}%",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 📃 Description
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 🛒 Action Buttons
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            ) {
                Button(
                    onClick = {
                        cartViewModel.addToCart(product)
                        Toast.makeText(context, "${product.title} added to cart", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Add to Cart")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        wishlistViewModel.addToWishlist(product)
                        Toast.makeText(context, "${product.title} added to wishlist", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Wishlist")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        // TODO: Navigate to Checkout (optional)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Buy Now")
                }
            }
        }
        } ?: Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
