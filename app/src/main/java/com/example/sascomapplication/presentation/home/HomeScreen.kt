package com.example.sascomapplication.presentation.home

import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sascomapplication.presentation.home.CategoryItem
import com.example.sascomapplication.presentation.home.ProductItem
import com.example.sascomapplication.domain.model.Product

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onProductClick: (Product) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Categories", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow {
            items(state.categories) { category ->
                CategoryItem(category = category) {
                    viewModel.onCategorySelected(category)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Products", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(state.filteredProducts) { product ->
                ProductItem(product = product, onClick = { onProductClick(product) })
            }
        }
    }
}