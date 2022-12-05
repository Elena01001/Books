package ru.netology.books.data


import retrofit2.http.GET
import retrofit2.http.Query
import ru.netology.books.domain.model.Book

interface GoogleApi {

    @GET("/books/v1/volumes/")
    suspend fun getBooksList(
        @Query("q") inTitle: String,
//        @Query("key") apiKey: String
    ): Result<List<Book>>

}
