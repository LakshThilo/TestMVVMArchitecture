package com.example.mvvmarchitechture.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmarchitechture.model.local.entities.FavouriteBook
import io.reactivex.rxjava3.core.Observable


/** Room is abstract implementation over the SQLite database
 *  - Rooms provide extra functionality that Sqlite is not able to give
 *  - part of android JetPack components
 *  - flexibility to check compile time if the select statement is correct
 *  - flexibility to create table using Annotation*/


/*
*   Data Access
*   Provide a easy way to access the transaction to the DB
* */

@Dao
interface BookDao {

    //Insert
    @Insert(entity = FavouriteBook::class, onConflict = OnConflictStrategy.REPLACE) //asking KClass
    fun insertFavouriteBook(book: FavouriteBook)

    //Select
    @Query(value="SELECT * FROM favourite_book")
    fun getFavouriteBook(): Observable<List<FavouriteBook>>
}

/**
 *  Steps to create Room DB
 *      0. Add dependencies
 *      1. Create the Dao Interface
 *      2. Create the Entities
 *      3. Create the Room DatabaseBuilder(thread safe)
 *      4. Create the transactions in the Dao
 *  */