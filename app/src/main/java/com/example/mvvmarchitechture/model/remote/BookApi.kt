package com.example.mvvmarchitechture.model.remote


import com.example.mvvmarchitechture.model.BookResponse
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {

    //http://books.some.som/
    // book/{id}/volumes ?
    // q=thelord of the ring &
    // maxRe=484884 &
    // print=kdkdkdkd

    @GET("books/v1/volumes")
    fun getBooksByName(
        @Query("q")
        bookName: String = "the lord of the rings++",
        @Query("maxResults")
        maxResults: Int = 5,
        @Query("printType")
        printType: String = "books"
    ): Observable<Response<BookResponse>>

    companion object{
        fun getApi(): BookApi =
            Retrofit.Builder()
                .client(createClient())
                .baseUrl("https://www.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(BookApi::class.java)

        private fun createClient(): OkHttpClient{
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder().addInterceptor(logging).build()
        }
    }
}