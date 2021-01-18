package com.example.madcapstone.db

import android.content.Context

class ProductRepository(context: Context) {
    private lateinit var productDao: ProductDao

    init {
        val productRoomDatabase = ProductRoomDatabase.getDatabase(context)
        productDao = productRoomDatabase!!.productDao()
    }

    suspend fun getAllProducts(): List<Product> {
        return productDao.getAllProducts()
    }

    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        productDao.deleteProduct(product)
    }

    suspend fun updateProduct(product: Product) {
        productDao.updateProduct(product)
    }
}