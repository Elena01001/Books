package ru.netology.books.data

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import ru.netology.books.domain.model.BookItems
import kotlin.test.assertNotNull

class TestNetworkRepositoryImpl {

    private lateinit var repository: NetworkRepositoryImpl

    @Mock
    private lateinit var api: GoogleApi

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = NetworkRepositoryImpl(api)
    }

    @Test
    fun getBooksListByTitle_Verify() = runBlocking<Unit> {
        val result: Result<BookItems>? = null
        `when`(api.getBooksList(anyString())).thenReturn(result)
        repository.getBooksListByTitle(anyString())
        verify(api, atLeastOnce()).getBooksList(anyString())
    }

    @Test
    fun getBooksListByTitle_NotNull() = runBlocking<Unit> {
        val result: Result<BookItems>? = null
        `when`(api.getBooksList(anyString())).thenReturn(result)
        val repoBookList = repository.getBooksListByTitle(anyString())
        assertNotNull(repoBookList)
    }

    @Test
    fun getBooksListByCategory_Verify() = runBlocking<Unit> {
        val result: Result<BookItems>? = null
        `when`(api.getBooksList(anyString())).thenReturn(result)
        repository.getBooksListByCategory(anyString())
        verify(api, atLeastOnce()).getBooksList(anyString())
    }

    @Test
    fun getBooksListByCategory_NotNull() = runBlocking<Unit> {
        val result: Result<BookItems>? = null
        `when`(api.getBooksList(anyString())).thenReturn(result)
        val repoBookList = repository.getBooksListByCategory(anyString())
        assertNotNull(repoBookList)
    }
}