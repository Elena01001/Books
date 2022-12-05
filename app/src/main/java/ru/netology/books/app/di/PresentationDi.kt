package ru.netology.books.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.netology.books.app.presentation.BookViewModel

val presentationDi = module {
    viewModel<BookViewModel> {
        BookViewModel(getBooksListUseCase = get())
    }
}