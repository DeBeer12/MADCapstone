package com.example.madcapstone.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productTable")

data class Product (
    @ColumnInfo(name = "productAmount")
    var productText: Double,
    @ColumnInfo(name = "productStore")
    var storeText: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)