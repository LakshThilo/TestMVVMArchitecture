package com.example.mvvmarchitechture.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmarchitechture.view.contract.IBookResultFragment
import com.example.mvvmarchitechture.databinding.DisplayBooksSearchBinding
import com.example.mvvmarchitechture.model.BookResponse
import com.example.mvvmarchitechture.view.adapter.BookAdapter
import com.example.mvvmarchitechture.viewmodel.BookViewModel


// Implementation of View Model and LiveData Connection / Consume lIVE DATA

class BookResultFragment :Fragment(), IBookResultFragment {

    companion object {
        private const val EXTRA_BOOK_NAME = " EXTRA_BOOK_NAME "
        fun newInstance(bookInput: String) =
            BookResultFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_BOOK_NAME, bookInput)
                }
            }

    }

   // private lateinit var presenter : BookResultPresenter

    /**
     *  viewModelProvider
     *  .get("DEFAULT_KEY +" + ":" + BookViewModel) -> means get me from the map these key entry
     *
     *  if null, put a new entry in the ViewModelStore [DefKey, BookViewModel]
     *
     *  return viewModel
     * */

    // we have a map internally keeping previous instance of the specific viewModel in this lines of code  checks
    // is it already being created or not if not create new one else return previously created one
    private val viewModel: BookViewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return BookViewModel() as T
                }
            }
        ).get(BookViewModel::class.java) // this is collection in kotlin there for can use bracket to get value and this is java syntax
       //[BookViewModel::class.java] // same as above - this is index for the element- this is map
   }

    private lateinit var binding: DisplayBooksSearchBinding


    override fun onBind() {
       // presenter.onBind(this)
    }

    override fun unBind() {
       // presenter.unBind()
    }

    override fun displayData(dataSet: BookResponse) {

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         super.onCreateView(inflater, container, savedInstanceState)
         binding = DisplayBooksSearchBinding.inflate(
             inflater,
             container,
             false
         )

        arguments?.getString(EXTRA_BOOK_NAME)?.let {
        //   presenter.searchBook(it)


            // Consuming ->>>>>>
            viewModel.searchBookName(it)
        }

        // this is observer
        viewModel.resultData.observe(viewLifecycleOwner,

            object: Observer<BookResponse>{
                override fun onChanged(t: BookResponse?) {
                    // this is going to be received in Main Thread
                    initViews(t)
                }
            }
           /* { t -> // this is going to be received in Main Thread  // ->> above code replace with lambda
                initViews(t)
            }*/)

        return binding.root
    }

    private fun initViews(t: BookResponse?) {
        //safe call
        t?.let { it ->
            binding.bookList.layoutManager = LinearLayoutManager(activity)
            // using the RVA
            binding.bookList.adapter = BookAdapter(it){
                // trailing lambda -> callback lambda - another option use method reference or creating function ur self
                // when user lick on button

                Toast.makeText(activity, "$it", Toast.LENGTH_SHORT).show()

                // BookAdapter - need to parameters @param1 - dataSet, @param2 {} - is lambda callback,
                // this block of code define callback function that passing to RVA
            }
        }

    }
}