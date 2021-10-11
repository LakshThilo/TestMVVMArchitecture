package com.example.mvvmarchitechture.view.contract

import com.example.mvvmarchitechture.model.BookResponse


interface IBookResultFragment {
    // receive data
    fun onBind()
    fun unBind()
    fun displayData(dataset: BookResponse)
}