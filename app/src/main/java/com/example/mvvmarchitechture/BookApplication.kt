package com.example.mvvmarchitechture

import android.app.Application
import com.example.mvvmarchitechture.model.local.BookDao
import com.example.mvvmarchitechture.model.local.BooksRoomDatabase


class BookApplication :Application() {


    /**
     * Init any library before MainActivity*/
    override fun onCreate() {
        super.onCreate()
        bookDao = BooksRoomDatabase.newInstance(this).getDao()
    }

    companion object {

        lateinit var bookDao: BookDao
    }
}