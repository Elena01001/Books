package ru.netology.books.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val publicationDate: String,
    val image: String
)

@JsonClass(generateAdapter = true)
data class BookResponse(
    val id: String,
    val volumeInfo: VolumeInfo
)

@JsonClass(generateAdapter = true)
data class VolumeInfo(
    val title: String,
    val subtitle: String,
    val authors: List<String>?,
    val publishedDate: String?,
    val description: String,
    val imageLinks: ImageLinks?
)

@JsonClass(generateAdapter = true)
data class BookItems(
    val items: List<BookResponse>
)

data class ImageLinks(
    val smallThumbnail: String?,
    val thumbnail: String?
)

fun BookResponse.toBook() = Book(
    id = this.id.hashCode(),
    title = volumeInfo.title,
    description = volumeInfo.description ?: "",
    author = volumeInfo.authors?.joinToString() ?: "",
    publicationDate = volumeInfo.publishedDate ?: "",
    image = volumeInfo.imageLinks?.smallThumbnail ?: ""
)