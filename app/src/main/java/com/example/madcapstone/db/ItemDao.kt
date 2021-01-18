package com.example.madcapstone.db

import androidx.room.*

@Dao
interface ItemDao {

    @Query("SELECT * FROM itemTable")
    suspend fun getAllItems(): List<Item>

    @Insert
    suspend fun insertItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)
}