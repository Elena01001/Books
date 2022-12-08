package ru.netology.books.domain.model

import com.squareup.moshi.JsonClass

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
    val categories: List<String>?,
    val publishedDate: String?,
    val description: String,
    val imageLinks: ImageLinks?
)

@JsonClass(generateAdapter = true)
data class BookItems(
    val items: List<BookResponse>?
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
    category = volumeInfo.categories?.joinToString() ?: "",
    image = volumeInfo.imageLinks?.smallThumbnail?.replace("http:","https:") ?: ""
)