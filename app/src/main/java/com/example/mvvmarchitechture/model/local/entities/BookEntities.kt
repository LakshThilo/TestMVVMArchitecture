package com.example.mvvmarchitechture.model.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/* Entity going to define
    * - Create the Table and Column used in this DB
    *  */

@Entity(tableName = "favourite_book") // this entity also going to represent object in kotlin code
data class FavouriteBook(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "book_image")
    val image: String,
    val title: String,
    val description: String,
    val author: String
)

