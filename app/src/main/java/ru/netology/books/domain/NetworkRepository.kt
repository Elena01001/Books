package ru.netology.books.domain


import ru.netology.books.domain.model.BookItems

interface NetworkRepository {

    suspend fun getBooksListByTitle(title: String): Result<BookItems>
    suspend fun getBooksListByCategory(category: String): Result<BookItems>
}