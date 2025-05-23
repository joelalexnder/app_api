package com.example.app_api.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_api.data.api.RetrofitClient
import com.example.app_api.data.model.ProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _productList = MutableStateFlow<List<ProductModel>>(emptyList())
    val productList: StateFlow<List<ProductModel>> = _productList

    private var currentPage = 1
    private val pageSize = 10
    private var isLoading = false

    init {
        loadMoreProducts()
    }

    fun loadMoreProducts() {
        if (isLoading) return
        isLoading = true

        viewModelScope.launch {
            try {
                val response = RetrofitClient.productApiService.getProducts()
                val newProducts = response.take(pageSize * currentPage)
                _productList.value = newProducts
                currentPage++
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}


