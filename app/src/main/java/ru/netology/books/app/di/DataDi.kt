package ru.netology.books.app.di

import org.koin.dsl.module
import ru.netology.books.data.GoogleApi
import ru.netology.books.data.NetworkRepositoryImpl
import ru.netology.books.data.Retrofit
import ru.netology.books.domain.NetworkRepository

val dataDi = module {

    single<GoogleApi> {
        Retrofit.api
    }

    single<NetworkRepository> {
        NetworkRepositoryImpl(googleApi = get())
    }
}