package com.example.madcapstone.db

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey


data class Band(
    @ColumnInfo(name = "name")
    var itemText: String,
    @ColumnInfo(name = "time")
    var timeText: String,
    @ColumnInfo(name = "stage")
    var stageText: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)
