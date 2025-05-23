package com.example.app_api.data.api

import com.example.app_api.data.model.ProductModel
import retrofit2.http.GET

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductModel>
}



