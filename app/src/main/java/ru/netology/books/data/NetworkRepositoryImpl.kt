package ru.netology.books.data

import ru.netology.books.domain.NetworkRepository
import ru.netology.books.domain.model.Book

class NetworkRepositoryImpl(
    private val googleApi: GoogleApi
) : NetworkRepository {
    override suspend fun getBooksList(search: String): Result<List<Book>> {
        return googleApi.getBooksList(search)
    }
}