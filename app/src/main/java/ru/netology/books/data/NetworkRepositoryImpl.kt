package ru.netology.books.data

import androidx.paging.PagingData
import ru.netology.books.domain.NetworkRepository
import ru.netology.books.domain.model.Book

class NetworkRepositoryImpl(
    private val googleApi: GoogleApi
) : NetworkRepository {
    override suspend fun getBooksList(search: String, apiKey: String): List<Book> {
        return googleApi.getBooksList(search, apiKey)
    }
}