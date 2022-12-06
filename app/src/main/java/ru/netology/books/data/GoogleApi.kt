package ru.netology.books.data


import retrofit2.http.GET
import retrofit2.http.Query
import ru.netology.books.domain.model.BookItems

interface GoogleApi {

    @GET("/books/v1/volumes/")
    suspend fun getBooksListByTitle(
        @Query("q") inTitle: String
    ): Result<BookItems>

    @GET("/books/v1/volumes/")
    suspend fun getBooksListByCategory(
        @Query("q=subject:") subject: String
    ): Result<BookItems>
}
