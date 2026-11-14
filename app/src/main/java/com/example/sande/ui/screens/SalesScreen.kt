package com.example.sande.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sande.di.SandeApplication
import com.example.sande.ui.formatKes
import com.example.sande.ui.viewModelFactoryOf
import com.example.sande.ui.viewmodel.SalesViewModel

@Composable
fun SalesScreen(modifier: Modifier = Modifier) {
    val app = LocalContext.current.applicationContext as SandeApplication
    val viewModel: SalesViewModel = viewModel(
        factory = viewModelFactoryOf { SalesViewModel(app.movementRepository, app.productRepository) }
    )
    val products by viewModel.products.collectAsState()
    val cart by viewModel.cart.collectAsState()

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text("Sales", style = MaterialTheme.typography.headlineMedium)

        Text("Products", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 12.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(products, key = { it.id }) { product ->
                ListItem(
                    headlineContent = { Text(product.name) },
                    supportingContent = { Text("KES ${formatKes(product.priceCents)}") },
                    trailingContent = {
                        IconButton(onClick = { viewModel.addToCart(product) }) {
                            Icon(Icons.Default.Add, contentDescription = "Add to cart")
                        }
                    }
                )
            }
        }

        Divider()
        Text("Cart", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 8.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(cart, key = { it.productId }) { item ->
                ListItem(
                    headlineContent = { Text(item.productName) },
                    supportingContent = {
                        Text("${item.quantity} × KES ${formatKes(item.unitPriceCents)}")
                    },
                    trailingContent = {
                        IconButton(onClick = { viewModel.removeFromCart(item.productId) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Remove")
                        }
                    }
                )
            }
        }
        Button(
            onClick = { viewModel.recordSale() },
            enabled = cart.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Record sale (KES ${formatKes(viewModel.subtotalCents(cart))})")
        }
    }
}
