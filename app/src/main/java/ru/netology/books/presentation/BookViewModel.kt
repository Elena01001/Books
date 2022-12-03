package ru.netology.books.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.netology.books.domain.model.Book
import ru.netology.books.domain.usecase.GetBooksListUseCase
import ru.netology.books.presentation.adapter.BookInteractionListener
import ru.netology.books.presentation.util.SingleLiveEvent

class BookViewModel(
    application: Application,
    /*private val getBooksListUseCase: GetBooksListUseCase*/ // TODO ПОЧЕМУ-ТО ПАДАЕТ С ЭТОЙ СТРОКОЙ
) : AndroidViewModel(application), BookInteractionListener {

    private val bookDetailsViewEvent = SingleLiveEvent<Int>()

    override fun onBookCardClicked(book: Book) {
        bookDetailsViewEvent.value = book.id
    }

    override fun onSearchBookClicked(book: Book) {
        TODO("Not yet implemented")
    }


}