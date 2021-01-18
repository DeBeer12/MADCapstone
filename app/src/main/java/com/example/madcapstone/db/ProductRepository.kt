package com.example.madcapstone.db

import android.content.Context

class ItemRepository(context: Context) {
    private var itemDao: ItemDao

    init {
        val reminderRoomDatabase = ItemRoomDatabase.getDatabase(context)
        itemDao = reminderRoomDatabase!!.itemDao()
    }

    suspend fun getAllItems(): List<Item> {
        return itemDao.getAllItems()
    }

    suspend fun insertItem(item: Item) {
        itemDao.insertItem(item)
    }

    suspend fun deleteItem(item: Item) {
        itemDao.deleteItem(item)
    }

    suspend fun updateItem(item: Item) {
        itemDao.updateItem(item)
    }
}