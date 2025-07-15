package com.example.sascomapplication.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sascomapplication.domain.model.Product

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel(),
    onProductClick: (Product) -> Unit
) {
    val query by viewModel.query.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = { viewModel.updateQuery(it) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Search products") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (searchResults.isEmpty() && query.isNotEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No products found.")
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(searchResults) { product ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onProductClick(product) }
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(product.title, style = MaterialTheme.typography.titleMedium)
                            Text("₹${product.price}", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
