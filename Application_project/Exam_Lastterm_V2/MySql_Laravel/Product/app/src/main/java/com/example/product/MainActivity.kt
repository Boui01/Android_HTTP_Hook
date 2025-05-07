package com.example.product

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.product.ui.theme.ProductTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val productViewModel: ProductViewModel by viewModels()
                    ProductList(productViewModel)
                }
            }
        }
    }
}

@Composable
fun ProductList(productViewModel: ProductViewModel) {
    val products = productViewModel.products

    LazyColumn {
        items(products) {
            Text(text = "ID: ${it.id}, Name: ${it.name}, Price: ${it.price}, Stock: ${it.stock}")
        }
    }
}