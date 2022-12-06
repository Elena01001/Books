package ru.netology.books.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.netology.books.app.presentation.adapter.BookInteractionListener
import ru.netology.books.domain.model.Book
import ru.netology.books.domain.model.toBook
import ru.netology.books.domain.usecase.GetBooksListByCategoryUseCase

class SearchByCategoryViewModel(
    private val getBooksListByCategoryUseCase: GetBooksListByCategoryUseCase
) : ViewModel(), BookInteractionListener {

    private var getBookListEvent = MutableStateFlow<BookState>(BookState.START)
    val booksFlow: StateFlow<BookState> = getBookListEvent

    fun getBookList(title: String) {
        viewModelScope.launch {
            getBooksListByCategoryUseCase.execute(title).onSuccess {
                val bookListResponse = it.items.map { it.toBook() }
                getBookListEvent.emit(BookState.SUCCESS(bookListResponse))
            }
                .onFailure {
                    getBookListEvent.emit(BookState.FAILURE(it.localizedMessage!!))
                }
        }
    }

    override fun onBookCardClicked(book: Book) {
        TODO("Not yet implemented")
    }

}


