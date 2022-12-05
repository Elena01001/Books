package ru.netology.books.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.netology.books.data.Constants.Companion.BASE_URL
import ru.netology.books.data.exception.ResultCallAdapterFactory

object Retrofit {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }

    val api: GoogleApi by lazy {
        retrofit.create(GoogleApi::class.java)
    }

}

class Constants {
    companion object {
        const val BASE_URL = "https://www.googleapis.com"
    }
}