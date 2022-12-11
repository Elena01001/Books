package ru.netology.books.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filterNotNull
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

    private var singleShotEvent = MutableSharedFlow<BookState>()
    val singleShotFlow: Flow<BookState> = singleShotEvent

    private var getBookListSuccessEvent = MutableSharedFlow<List<Book>>(1, 1)
    val successBooksFlow: Flow<List<Book>> = getBookListSuccessEvent

    fun getBookList(title: String) {
        viewModelScope.launch {
            getBooksListByTitleUseCase.execute(title).onSuccess {
                if (it.items != null) {
                    val bookListResponse = it.items.map { it.toBook() }
                    getBookListSuccessEvent.emit(bookListResponse)
                } else {
                    singleShotEvent.emit(BookState.EMPTY)
                    getBookListSuccessEvent.emit(emptyList())
                }

            }
                .onFailure {
                    singleShotEvent.emit(BookState.FAILURE(it.localizedMessage.toString()))
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
    object EMPTY : BookState()
    data class FAILURE(val message: String) : BookState()
}
