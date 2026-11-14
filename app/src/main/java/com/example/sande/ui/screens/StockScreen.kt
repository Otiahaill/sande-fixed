package com.example.sande.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.sande.ui.viewModelFactoryOf
import com.example.sande.ui.viewmodel.StockViewModel

@Composable
fun StockScreen(modifier: Modifier = Modifier) {
    val app = LocalContext.current.applicationContext as SandeApplication
    val viewModel: StockViewModel = viewModel(
        factory = viewModelFactoryOf { StockViewModel(app.movementRepository, app.productRepository) }
    )
    val rows by viewModel.rows.collectAsState()

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text("Stock", style = MaterialTheme.typography.headlineMedium)
        if (rows.isEmpty()) {
            Text("No products yet.", modifier = Modifier.padding(top = 12.dp))
        } else {
            LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
                items(rows, key = { it.product.id }) { row ->
                    ListItem(
                        headlineContent = { Text(row.product.name) },
                        trailingContent = { Text("${row.balance}") }
                    )
                }
            }
        }
    }
}
