package ru.netology.books.domain.usecase

import ru.netology.books.domain.NetworkRepository
import ru.netology.books.domain.model.BookItems

class GetBooksListByTitleUseCase (
    private val networkRepository: NetworkRepository
) {
    suspend fun execute(title: String): Result<BookItems> {
        return networkRepository.getBooksListByTitle(title)
    }
}