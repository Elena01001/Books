package ru.netology.books.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.netology.books.app.presentation.adapter.BookInteractionListener
import ru.netology.books.domain.model.Book
import ru.netology.books.domain.model.toBook
import ru.netology.books.domain.usecase.GetBooksListByTitleUseCase

class SearchByTitleViewModel(
    private val getBooksListByTitleUseCase: GetBooksListByTitleUseCase
) : ViewModel(), BookInteractionListener {

    private val bookDetailsSharedFlow = MutableSharedFlow<Book>()
    val bookDetailsFlow: Flow<Book> = bookDetailsSharedFlow.filterNotNull()

    private var getEmptyListEvent = MutableSharedFlow<List<Book>>()
    val emptyListFlow: Flow<List<Book>> = getEmptyListEvent

    private var getBookListEvent = MutableSharedFlow<BookState>(1, 1)
    val booksFlow: Flow<BookState> = getBookListEvent

    fun getBookList(title: String) {
        viewModelScope.launch {
            getBooksListByTitleUseCase.execute(title).onSuccess {
                if (it.items != null) {
                    val bookListResponse = it.items.map { it.toBook() }
                    getBookListEvent.emit(BookState.SUCCESS(bookListResponse))
                } else {
                    getEmptyListEvent.emit((emptyList()))
                    getBookListEvent.emit(BookState.START)
                }

            }
                .onFailure {
                    getBookListEvent.emit(BookState.FAILURE(it.localizedMessage.toString()))
                }
        }
    }

    override fun onBookCardClicked(book: Book) {
        viewModelScope.launch {
            bookDetailsSharedFlow.emit(book)
        }
    }
}

sealed class BookState {
    object START : BookState()
    data class SUCCESS(val books: List<Book?>) : BookState()
    data class FAILURE(val message: String) : BookState()
}
