package ru.netology.books.app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.netology.books.app.presentation.adapter.BookInteractionListener
import ru.netology.books.data.Retrofit.api
import ru.netology.books.domain.model.Book
import ru.netology.books.domain.usecase.GetBooksListUseCase

class BookViewModel(
    private val getBooksListUseCase: GetBooksListUseCase
) : ViewModel(), BookInteractionListener {

    private val bookDetailsViewEvent = MutableSharedFlow<Int>()

    var bookListResponse: List<Book> = listOf()

    private var getBookListEvent = MutableStateFlow<BookState>(BookState.START)
    val booksFlow: StateFlow<BookState> = getBookListEvent

    fun getBookList(title: String) {
        viewModelScope.launch {
            getBooksListUseCase.execute(title).onSuccess {
                bookListResponse = it
                getBookListEvent.emit(BookState.SUCCESS)
            }
                .onFailure {
                    getBookListEvent.emit(BookState.FAILURE(it.localizedMessage!!))
                }
        }
    }


    override fun onBookCardClicked(book: Book) {
        /*bookDetailsViewEvent.emit(book.id)*/ //TODO может работать только внутри suspend или корутины, продумать пвоедение
    }

    fun onSearchBookClicked(book: Book) {
        TODO("Not yet implemented")
    }

}

sealed class BookState {
    object START : BookState()
    object SUCCESS : BookState()
    data class FAILURE(val message: String) : BookState()
}
