package ru.netology.books.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.netology.books.domain.model.Book

interface NetworkRepository {

    suspend fun getBooksList(search: String): Result<List<Book>>
}