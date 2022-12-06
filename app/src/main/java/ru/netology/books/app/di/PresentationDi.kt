package ru.netology.books.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.netology.books.app.presentation.viewmodel.SearchByCategoryViewModel
import ru.netology.books.app.presentation.viewmodel.SearchByTitleViewModel

val presentationDi = module {
    viewModel<SearchByTitleViewModel> {
        SearchByTitleViewModel(getBooksListByTitleUseCase = get())
    }

    viewModel<SearchByCategoryViewModel> {
        SearchByCategoryViewModel(getBooksListByCategoryUseCase = get())
    }
}