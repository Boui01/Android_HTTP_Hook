package com.example.product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {
    var products:List<Product> by mutableStateOf(emptyList())
    init{
        getProducts()
    }
    private fun getProducts(){
        viewModelScope.launch {
            products = ProductService().productService.getProducts()
        }
    }
}