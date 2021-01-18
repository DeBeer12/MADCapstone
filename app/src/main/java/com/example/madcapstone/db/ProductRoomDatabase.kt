package com.example.madcapstone.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemRoomDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        private const val DATABASE_NAME = "ITEM_DATABASE"

        @Volatile
        private var itemRoomDatabaseInstance: ItemRoomDatabase? = null

        fun getDatabase(context: Context): ItemRoomDatabase? {
            if (itemRoomDatabaseInstance == null) {
                synchronized(ItemRoomDatabase::class.java) {
                    if (itemRoomDatabaseInstance == null) {
                        itemRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            ItemRoomDatabase::class.java, DATABASE_NAME
                        )
//                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return itemRoomDatabaseInstance
        }
    }
}