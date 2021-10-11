package com.example.mvvmarchitechture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmarchitechture.view.BookSearchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.search_fragment_container, BookSearchFragment())
            .commit()
    }
}