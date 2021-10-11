package com.example.mvvmarchitechture.presenter

import com.example.httpurlconnectiontest.view.contract.IBookSearchFragment


/**
     * Bridge for BookSearchFragment
     * DO NOT contain any Android/Fragment reference
     *
    **/
class BookSearchPresenter {

        private  var view: IBookSearchFragment? = null

    fun onBind(bookResultFragment: IBookSearchFragment) {
            view = bookResultFragment
    }

    fun unBind() {
        view = null
    }

    fun sendRequest(inputBook: String) {

        view?.navigateResultFragment(inputBook)

    }

}
