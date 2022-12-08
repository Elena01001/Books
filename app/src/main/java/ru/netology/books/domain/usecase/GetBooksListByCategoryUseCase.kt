package ru.netology.books.domain.usecase

import ru.netology.books.domain.NetworkRepository
import ru.netology.books.domain.model.BookItems

class GetBooksListByCategoryUseCase(
    private val networkRepository: NetworkRepository
) {
    suspend fun execute(category: String): Result<BookItems> {
        return networkRepository.getBooksListByCategory(category)
    }
}