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
import ru.netology.books.domain.usecase.GetBooksListByCategoryUseCase

class SearchByCategoryViewModel(
    private val getBooksListByCategoryUseCase: GetBooksListByCategoryUseCase
) : ViewModel(), BookInteractionListener {

    private val bookDetailsEvent = MutableSharedFlow<Book?>()
    val detailsFlow: Flow<Book> = bookDetailsEvent.filterNotNull()

    private var singleShotSharedFlow = MutableSharedFlow<BookState>()
    val singleShotEventFlow: Flow<BookState> = singleShotSharedFlow

    private var getBooksSuccessEvent = MutableSharedFlow<List<Book>>(1, 1)
    val bookSuccessFlow: Flow<List<Book>> = getBooksSuccessEvent

    fun getBookList(category: String) {
        viewModelScope.launch {
            getBooksListByCategoryUseCase.execute(category).onSuccess {
                if (it.items != null) {
                    val bookListResponse = it.items.map { it.toBook() }
                    getBooksSuccessEvent.emit(bookListResponse)
                } else {
                    singleShotSharedFlow.emit(BookState.EMPTY)
                    getBooksSuccessEvent.emit((emptyList()))
                }
            }
                .onFailure {
                    singleShotSharedFlow.emit(BookState.FAILURE(it.localizedMessage.toString()))
                }
        }
    }

    override fun onBookCardClicked(book: Book) {
        viewModelScope.launch {
            bookDetailsEvent.emit(book)
        }
    }

}


