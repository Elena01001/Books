package ru.netology.books.domain.usecase

import androidx.paging.PagingData
import ru.netology.books.domain.NetworkRepository
import ru.netology.books.domain.model.Book

class GetBooksListUseCase (
    private val networkRepository: NetworkRepository
) {
    suspend fun execute(search: String, apiKey: String): List<Book> {
        return networkRepository.getBooksList(search, apiKey)
    }
}