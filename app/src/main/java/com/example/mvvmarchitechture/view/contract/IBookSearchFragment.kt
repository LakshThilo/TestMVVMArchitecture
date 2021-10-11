package com.example.httpurlconnectiontest.view.contract

interface IBookSearchFragment {
    // input a book name
    // send request to Presenter
    fun bindPresenter()
    /**
     * We have lifecycle components, to avoid memory leaks
     */
    fun unBindPresenter()
    fun sendRequestToPresenter()
    fun navigateResultFragment(inputBook: String)
}