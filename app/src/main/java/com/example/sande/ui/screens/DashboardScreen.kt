package com.example.sande.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Icons
import androidx.compose.material3.ImageVector
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.sande.di.SandeApplication
import com.example.sande.data.entities.Product
import com.example.sande.ui.theme.SandeShape
import com.example.sande.ui.viewmodel.CatalogueViewModel
import com.example.sande.ui.viewmodel.StockViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val app = context.applicationContext as SandeApplication
    val viewModel: CatalogueViewModel = viewModel {
        CatalogueViewModel(app.productRepository)
    }
    val stockViewModel: StockViewModel = viewModel {
        StockViewModel(app.movementRepository)
    }
    val products: List<Product> by viewModel.products.collectAsState()
    val stockByProduct: Map<String, Long> by stockViewModel.stockByProduct.collectAsState()

    val totalInventoryValue = products.sumOf { p ->
        val stock = stockByProduct[p.id] ?: 0L
        stock * p.priceCents
    } / 100.0

    val lowStockCount = products.count { p ->
        val stock = stockByProduct[p.id] ?: 0L
        stock <= 5
    }

    val topSellingValue = products.sumOf { p ->
        val stock = stockByProduct[p.id] ?: 0L
        stock * p.priceCents
    } / 100.0

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Invenio",
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Inventory Dashboard",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // KPI Cards Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                KpiCard(
                    title = "Total Inventory Value",
                    value = formatCurrency(totalInventoryValue),
                    subtitle = "Across all products",
                    icon = Icons.Default.Inventory,
                    iconColor = MaterialTheme.colorScheme.primary,
                    gradientColor = Color(0xFFE3F2FD)
                )
                KpiCard(
                    title = "Low Stock Items",
                    value = lowStockCount.toString(),
                    subtitle = "Needs attention",
                    icon = Icons.Default.Storage,
                    iconColor = MaterialTheme.colorScheme.error,
                    gradientColor = Color(0xFFFFEEEE)
                )
                KpiCard(
                    title = "Top Selling",
                    value = "\$${topSellingValue.toInt()}",
                    subtitle = "This period",
                    icon = Icons.Default.TrendingUp,
                    iconColor = MaterialTheme.colorScheme.secondary,
                    gradientColor = Color(0xFFE4E4EC)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Inventory Section Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Inventory",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Search for products",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Inventory List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp)
            ) {
                items(
                    items = products,
                    key = { it.id }
                ) { product ->
                    InventoryRow(
                        product = product,
                        stock = stockByProduct[product.id] ?: 0L
                    )
                }
            }
        }
    }
}

/**
 * Format currency value
 */
private fun formatCurrency(value: Double): String {
    val str = value.toString()
    val dotIndex = str.indexOf('.')
    if (dotIndex >= 0) {
        val decimals = str.substring(dotIndex + 1)
        val cents = if (decimals.length >= 2) decimals.substring(0, 2) else decimals + "0"
        return "\$${str.substring(0, dotIndex)}.$cents"
    }
    return "\$${str}.00"
}

/**
 * KPI Card with gradient background, large value, and icon
 */
@Composable
fun KpiCard(
    title: String,
    value: String,
    subtitle: String,
    icon: ImageVector,
    iconColor: Color,
    gradientColor: Color
) {
    val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels / LocalContext.current.resources.displayMetrics.density
    val cardWidth = (screenWidth - 36 - 24) / 3.0

    Card(
        modifier = Modifier
            .width(cardWidth.dp)
            .height(130.dp),
        shape = SandeShape.kpiCard,
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Icon + Gradient badge
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(36.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(gradientColor)
                    )
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(0.dp))
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Title
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = value,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

/**
 * Inventory Row with stock level indicator
 */
@Composable
fun InventoryRow(product: Product, stock: Long) {
    val isLowStock = stock <= 5

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* navigate to product detail */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Product icon placeholder
        Box(
            modifier = Modifier.size(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Inventory,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Product info
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
            Text(
                text = "SKU: ${product.sku ?: "N/A"}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Stock level
        Column(modifier = Modifier.width(80.dp)) {
            Text(
                text = stock.toString(),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
            if (isLowStock) {
                Text(
                    text = "Low",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.error
                )
            } else {
                Text(
                    text = "OK",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Unit price
        Text(
            text = "\$${product.priceCents / 100}.${product.priceCents % 100}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.width(70.dp)
        )
    }
}

/**
 * Simple Box alias for common usage
 */
@Composable
fun Box(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopLeft,
    content: @Composable () -> Unit = {}
) {
    androidx.compose.foundation.layout.Box(
        modifier = modifier,
        contentAlignment = contentAlignment,
        content = content
    )
}

/**
 * Simple Column alias
 */
@Composable
fun Column(
    modifier: Modifier = Modifier,
    verticalArrangement: androidx.compose.foundation.layout.Arrangement.Vertical = androidx.compose.foundation.layout.Arrangement.Top,
    horizontalAlignment: androidx.compose.foundation.layout.Alignment.Horizontal = androidx.compose.foundation.layout.Alignment.Start,
    content: @Composable () -> Unit
) {
    androidx.compose.foundation.layout.Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        content = content
    )
}

/**
 * Simple Row alias
 */
@Composable
fun Row(
    modifier: Modifier = Modifier,
    verticalAlignment: androidx.compose.foundation.layout.Alignment.Vertical = androidx.compose.foundation.layout.Alignment.Top,
    horizontalArrangement: androidx.compose.foundation.layout.Arrangement.Horizontal = androidx.compose.foundation.layout.Arrangement.Start,
    content: @Composable () -> Unit
) {
    androidx.compose.foundation.layout.Row(
        modifier = modifier,
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement,
        content = content
    )
}

/**
 * Simple Spacer
 */
@Composable
fun Spacer(modifier: Modifier = Modifier) {
    androidx.compose.foundation.layout.Spacer(modifier = modifier)
}
