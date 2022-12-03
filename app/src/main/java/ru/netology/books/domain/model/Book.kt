package ru.netology.books.domain.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val publicationDate: String,
    val image: String?
    ): Parcelable