package ru.netology.books.app.presentation.adapter

import ru.netology.books.domain.model.Book

interface BookInteractionListener {

    fun onBookCardClicked(book: Book)
}