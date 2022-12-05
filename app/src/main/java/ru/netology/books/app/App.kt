package ru.netology.books.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.netology.books.app.di.dataDi
import ru.netology.books.app.di.domainDi
import ru.netology.books.app.di.presentationDi

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            applicationContext
            modules(listOf(dataDi, domainDi, presentationDi))
        }
    }
}