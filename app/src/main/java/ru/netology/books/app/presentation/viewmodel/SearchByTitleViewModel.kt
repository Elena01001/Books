package ru.netology.books.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.netology.books.app.presentation.adapter.BookInteractionListener
import ru.netology.books.domain.model.Book
import ru.netology.books.domain.model.toBook
import ru.netology.books.domain.usecase.GetBooksListByTitleUseCase

class SearchByTitleViewModel(
    private val getBooksListByTitleUseCase: GetBooksListByTitleUseCase
) : ViewModel(), BookInteractionListener {

    private val bookDetailsViewEvent = MutableStateFlow<Book?>(null)
    val bookDetailsFlow: StateFlow<Book?> = bookDetailsViewEvent

    private var getBookListEvent = MutableStateFlow<BookState>(BookState.START)
    val booksFlow: StateFlow<BookState> = getBookListEvent

    fun getBookList(title: String) {
        viewModelScope.launch {
            getBooksListByTitleUseCase.execute(title).onSuccess {
                val bookListResponse = it.items?.map { it.toBook() } ?: emptyList()
                getBookListEvent.emit(BookState.SUCCESS(bookListResponse))
            }
                .onFailure {
                    getBookListEvent.emit(BookState.FAILURE(it.localizedMessage.toString()))
                }
        }
    }

    override fun onBookCardClicked(book: Book) {
        viewModelScope.launch {
            bookDetailsViewEvent.emit(book)
        }
    }
}

sealed class BookState {
    object START : BookState()
    data class SUCCESS(val books: List<Book?>) : BookState()
    data class FAILURE(val message: String) : BookState()
}
