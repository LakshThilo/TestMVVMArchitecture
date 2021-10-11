package com.example.mvvmarchitechture.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmarchitechture.databinding.SingleBookResponseBinding
import com.example.mvvmarchitechture.model.BookResponse
import com.example.mvvmarchitechture.model.VolumeInfoDescription


class BookAdapter(private val bookData: BookResponse, private val callback: (Int) -> Unit) :RecyclerView.Adapter<BookAdapter.BookViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        return BookViewHolder(
            SingleBookResponseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        holder.onBind(bookData.items[position].volumeInfo, callback)
    }

    override fun getItemCount() = bookData.items.size


    class BookViewHolder(private val binding: SingleBookResponseBinding): RecyclerView.ViewHolder(binding.root){

        fun onBind(dataItem: VolumeInfoDescription,
                   callback: (Int) -> Unit){ //(Int) -> Unit) -> here passing position

            binding.bookTitle.text = dataItem.title
            binding.bookAuthors.text = dataItem.authors.toString()

            binding.root.setOnClickListener { callback(adapterPosition) } // setOnClick Listener when user click entire row
            //adapterPosition -->> this current position of the viewHolder - list

            /*
            *  here we passing position not the the book it self - we gonna be using same viewModel
            * for both fragment and the viewModel is gonna be lifecycle aware, is going to have
            * reference of the data there for we can query by the position from the list of element
            * and display the detail of such position -> previously we have to pass data item to here
            * (Int) -> Unit), make the data item parcelable because need to pass data between fragment
            * in this case using viewModel  we can remove parcelable implementation
            * */
        }
    }

}