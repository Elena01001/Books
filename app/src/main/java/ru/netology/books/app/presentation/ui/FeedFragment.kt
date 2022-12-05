package ru.netology.books.app.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.netology.books.app.presentation.BookState
import ru.netology.books.databinding.FeedFragmentBinding
import ru.netology.books.app.presentation.BookViewModel
import ru.netology.books.app.presentation.adapter.BooksAdapter

class FeedFragment : Fragment() {

    /*AIzaSyCdWvYMtPl9P3E37YnBzdOvwBhD2L42RF0*/

    private val viewModel by viewModel<BookViewModel>()

    private var _binding: FeedFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FeedFragmentBinding.inflate(inflater, container, false)

        binding.progressBar.isVisible = false

        binding.searchButton.setOnClickListener {
            search()
        }

        val adapter = BooksAdapter(viewModel)
        binding.booksRecyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.booksFlow.collectLatest {
                when (it) {
                    is BookState.SUCCESS -> {
                        adapter.submitList(adapter.currentList)

                    }
                    is BookState.FAILURE -> {
                        val message = it.message
                        /*LaunchedEffect(key1 = message) {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }*/

                    }
                    else -> {}
                }
            }
        }
        return binding.root
    }

    private fun search() {
        binding.progressBar.isVisible = true
        val query = binding.searchText.editText?.text.toString()
        viewModel.getBookList(query)
        binding.progressBar.isVisible = false

    }

}