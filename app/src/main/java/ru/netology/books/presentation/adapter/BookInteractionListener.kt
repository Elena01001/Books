package ru.netology.books.presentation.adapter

import ru.netology.books.domain.model.Book

interface BookInteractionListener {

    fun onBookCardClicked(book: Book)
    fun onSearchBookClicked(book: Book)
}