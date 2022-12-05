package ru.netology.books.app.di

import org.koin.dsl.module
import ru.netology.books.domain.usecase.GetBooksListUseCase

val domainDi = module {
    factory<GetBooksListUseCase> {
        GetBooksListUseCase(networkRepository = get())
    }
}