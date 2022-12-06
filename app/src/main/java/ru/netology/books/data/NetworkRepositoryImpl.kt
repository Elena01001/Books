package ru.netology.books.data

import ru.netology.books.domain.NetworkRepository
import ru.netology.books.domain.model.BookItems

class NetworkRepositoryImpl(
    private val googleApi: GoogleApi
) : NetworkRepository {

    override suspend fun getBooksListByTitle(title: String): Result<BookItems> {
        return googleApi.getBooksListByTitle(title)
    }

    override suspend fun getBooksListByCategory(category: String): Result<BookItems> {
        return googleApi.getBooksListByCategory(category)
    }
}