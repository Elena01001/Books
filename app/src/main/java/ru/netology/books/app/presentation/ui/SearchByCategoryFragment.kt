package ru.netology.books.app.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.netology.books.R
import ru.netology.books.app.presentation.adapter.BooksAdapter
import ru.netology.books.app.presentation.utils.hideKeyboard
import ru.netology.books.app.presentation.viewmodel.BookState
import ru.netology.books.app.presentation.viewmodel.SearchByCategoryViewModel
import ru.netology.books.databinding.FeedFragmentBinding
import ru.netology.books.domain.model.Book

class SearchByCategoryFragment : Fragment() {

    private val viewModel by viewModel<SearchByCategoryViewModel>()

    private var _binding: FeedFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FeedFragmentBinding.inflate(inflater, container, false)

        binding.progressBar.isVisible = false
        binding.searchText.hint = getString(R.string.put_category)

        binding.searchButton.setOnClickListener {
            binding.root.hideKeyboard()
            binding.progressBar.isVisible = true
            val query = binding.searchText.editText?.text.toString()
            viewModel.getBookList(query)
        }

        val adapter = BooksAdapter(viewModel)
        binding.booksRecyclerView.adapter = adapter
        observeGetBooksByCategory(adapter)
        observeGetEmptyListByCategory(adapter)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.detailsFlow.collectLatest {
                onBookCardClick(it)
            }
        }

        return binding.root
    }

    private fun observeGetBooksByCategory(adapter: BooksAdapter) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.bookStateFlow.collect {
                binding.progressBar.isVisible = false
                when (it) {
                    is BookState.SUCCESS -> {
                        adapter.submitList(it.books)
                    }
                    is BookState.FAILURE -> {
                        val message = it.message
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                    is BookState.START -> {}
                }
            }
        }
    }

    private fun observeGetEmptyListByCategory(adapter: BooksAdapter) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.emptyBookListFlow.collect {
                binding.progressBar.isVisible = false
                adapter.submitList(emptyList())
                Toast.makeText(context, R.string.nothing_is_found, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onBookCardClick(book: Book) {
        val action =
            SearchByCategoryFragmentDirections.actionSearchByCategoryFragmentToBookDetailsFragment(
                book, CATEGORY
            )
        findNavController().navigate(action)

    }

    companion object {
        const val CATEGORY = "Category"
    }
}