package com.example.mvvmarchitechture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmarchitechture.model.BookResponse
import com.example.mvvmarchitechture.model.remote.BookApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * A container of Data. It will survive configuration changes.
 */
class BookViewModel: ViewModel() {

    /**
     * _{variable} means a backing field
     * Will be used in this class for value reference.
     */
    private val _resultData = MutableLiveData<BookResponse>()

    val resultData: LiveData<BookResponse>
    get()= _resultData

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String>
    get() = _errorResponse

    private val bookApi: BookApi by lazy {
        BookApi.getApi()
    }

    fun searchBookName(bookName: String){

        //BookApi.getApi() call like this
        bookApi.getBooksByName(bookName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                       // success / onNext
                       if (it.isSuccessful) //response code 200-300
                           _resultData.value = it.body()!!
            },{
                // onError/Failure
                _errorResponse.value = it.message
            })
    }

}
/*
class A{
private String name;

public String getName()

}
 */




