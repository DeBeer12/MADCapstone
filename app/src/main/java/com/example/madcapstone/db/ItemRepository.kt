package com.example.madcapstone.db

import android.content.Context

class ItemRepository(context: Context) {
    private var itemDao: ItemDao

    init {
        val reminderRoomDatabase = ItemRoomDatabase.getDatabase(context)
        itemDao = reminderRoomDatabase!!.itemDao()
    }

    suspend fun getAllReminders(): List<Item> {
        return itemDao.getAllItems()
    }

    suspend fun insertReminder(item: Item) {
        itemDao.insertItem(item)
    }

    suspend fun deleteReminder(item: Item) {
        itemDao.deleteItem(item)
    }

    suspend fun updateReminder(item: Item) {
        itemDao.updateItem(item)
    }
}