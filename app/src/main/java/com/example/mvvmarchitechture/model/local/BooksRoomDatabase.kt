package com.example.mvvmarchitechture.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmarchitechture.model.local.entities.FavouriteBook



    /**
     * Class to generate the Room DB builder
     * @param entities Define the Entities that will be part of this DB
     * @param version Current version schema
     * @param exportSchema Create a separate file with DB Schema
    * */
@Database(entities = [FavouriteBook::class], version = 1, exportSchema = true)
abstract class BooksRoomDatabase : RoomDatabase() {

    abstract fun getDao(): BookDao

    companion object {

         private var INSTANCE: BooksRoomDatabase? = null

        fun newInstance(context: Context) : BooksRoomDatabase {

            var temp = INSTANCE
            if (temp != null) return temp
            synchronized(this) {
                if (temp!= null) return temp as BooksRoomDatabase

                // create room database builder
                temp = Room.databaseBuilder(
                    context,
                    BooksRoomDatabase::class.java,
                    "book_room_db"
                )
                    //.allowMainThreadQueries() // this one only for testing/practicing not for the production code
                 .build()

                INSTANCE = temp
                return temp as BooksRoomDatabase
            }
        }
    }
}