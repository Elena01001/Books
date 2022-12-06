package ru.netology.books.app.di

import org.koin.dsl.module
import ru.netology.books.domain.usecase.GetBooksListByCategoryUseCase
import ru.netology.books.domain.usecase.GetBooksListByTitleUseCase

val domainDi = module {

    factory<GetBooksListByTitleUseCase> {
        GetBooksListByTitleUseCase(networkRepository = get())
    }

    factory<GetBooksListByCategoryUseCase> {
        GetBooksListByCategoryUseCase(networkRepository = get())
    }
}