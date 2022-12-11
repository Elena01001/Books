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

    private var getEmptyListSharedFlow = MutableSharedFlow<List<Book>>()
    val emptyBookListFlow: Flow<List<Book>> = getEmptyListSharedFlow

    private var getBooksEvent = MutableSharedFlow<BookState>(1, 1)
    val bookStateFlow: Flow<BookState> = getBooksEvent

    fun getBookList(category: String) {
        viewModelScope.launch {
            getBooksListByCategoryUseCase.execute(category).onSuccess {
                if (it.items != null) {
                    val bookListResponse = it.items.map { it.toBook() }
                    getBooksEvent.emit(BookState.SUCCESS(bookListResponse))
                } else {
                    getEmptyListSharedFlow.emit((emptyList()))
                    getBooksEvent.emit(BookState.START)
                }
            }
                .onFailure {
                    getBooksEvent.emit(BookState.FAILURE(it.localizedMessage.toString()))
                }
        }
    }

    override fun onBookCardClicked(book: Book) {
        viewModelScope.launch {
            bookDetailsEvent.emit(book)
        }
    }

}


