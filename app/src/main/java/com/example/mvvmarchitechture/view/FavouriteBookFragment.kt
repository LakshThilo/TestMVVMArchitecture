package com.example.mvvmarchitechture.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvvmarchitechture.BookApplication
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class FavouriteBookFragment :Fragment(){

    // todo RV adapter
    // todo getDao
    // todo Observe Dao Favourite

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)

        initObservable()

        return  null
    }

    private fun initObservable() {
        BookApplication.bookDao.getFavouriteBook()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //main thread, update the RV
                }, {

                    //error, show to the user and log te error
                }
            )
    }
}