package com.example.mvvmarchitechture.view

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.httpurlconnectiontest.view.contract.IBookSearchFragment
import com.example.mvvmarchitechture.R
import com.example.mvvmarchitechture.databinding.BookSearchFragmentBinding
import com.example.mvvmarchitechture.utilities.navigateResultFragment
import com.example.mvvmarchitechture.presenter.BookSearchPresenter

class BookSearchFragment : Fragment(), IBookSearchFragment {

    private lateinit var presenter: BookSearchPresenter
    private lateinit var binding: BookSearchFragmentBinding


    override fun bindPresenter() {
        if (::presenter.isInitialized)
            presenter.onBind(this)
        else {
            presenter = BookSearchPresenter()
            presenter.onBind(this)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bindPresenter()
    }

    override fun unBindPresenter() {
        presenter.unBind()
    }

    override fun onStart() {
        super.onStart()
        sendRequestToPresenter()
    }

    override fun sendRequestToPresenter() {
        presenter.sendRequest(getInputBook())
    }

    override fun navigateResultFragment(inputBook: String) {
        activity?.navigateResultFragment(inputBook)
    }

    private fun getInputBook(): String {
        return binding.bookSearchInput.editText?.text?.toString() ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BookSearchFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        initViews()

        return binding.root
    }

    private fun initViews() {
        binding.bookType.adapter = createBookTypesAdapter()
        binding.bookSearchInput.editText?.setOnEditorActionListener(::editorActionListener)
    }

    private fun createBookTypesAdapter(): ArrayAdapter<String>? {
        return activity?.let {
            ArrayAdapter(
                it, android.R.layout.simple_list_item_1,
                it.resources.getStringArray(R.array.book_type)
            )
        }
    }


    private fun editorActionListener(textView: TextView?, i: Int, keyEvent: KeyEvent?): Boolean {
        if (i == EditorInfo.IME_ACTION_SEARCH) {
            sendRequestToPresenter()
            return true
        }
        return false
    }
}





