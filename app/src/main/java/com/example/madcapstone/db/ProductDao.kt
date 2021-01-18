package com.example.madcapstone.db

import androidx.room.*

@Dao
interface ProductDao {

    @Query("SELECT * FROM productTable")
    suspend fun getAllProducts(): List<Product>

    @Insert
    suspend fun insertProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)
}