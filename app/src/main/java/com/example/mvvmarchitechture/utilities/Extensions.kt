package com.example.mvvmarchitechture.utilities

import androidx.fragment.app.FragmentActivity
import com.example.mvvmarchitechture.R
import com.example.mvvmarchitechture.view.BookResultFragment

fun FragmentActivity.navigateResultFragment(bookInput: String){
    supportFragmentManager.beginTransaction()
        .replace(R.id.result_fragment_container, BookResultFragment.newInstance(bookInput))
        .commit()
}