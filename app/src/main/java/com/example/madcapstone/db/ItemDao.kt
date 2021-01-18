package com.example.madcapstone.db

import androidx.room.*

@Dao
interface ItemDao {

    @Query("SELECT * FROM itemTable")
    suspend fun getAllItems(): List<Item>

    @Insert
    suspend fun insertItem(reminder: Item)

    @Delete
    suspend fun deleteItem(reminder: Item)

    @Update
    suspend fun updateItem(reminder: Item)
}