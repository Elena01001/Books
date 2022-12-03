package ru.netology.books.data


import retrofit2.http.GET
import retrofit2.http.Query
import ru.netology.books.domain.model.Book

interface GoogleApi {

    @GET(" ")
    suspend fun getBooksList(
        @Query("q") inTitle: String,
        @Query("key") apiKey: String
    ): List<Book> // где-то responce?

}
