package ru.netology.books.app.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.netology.books.R
import ru.netology.books.app.presentation.adapter.BooksAdapter
import ru.netology.books.app.presentation.viewmodel.BookState
import ru.netology.books.app.presentation.viewmodel.SearchByCategoryViewModel
import ru.netology.books.databinding.FeedFragmentBinding

class SearchByCategoryFragment : Fragment() {

    private val viewModel by viewModel<SearchByCategoryViewModel>()

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
        binding.searchText.hint = getString(R.string.put_category)

        binding.searchButton.setOnClickListener {
            binding.progressBar.isVisible = true
            val query = binding.searchText.editText?.text.toString()
            viewModel.getBookList(query)
        }

        val adapter = BooksAdapter(viewModel)
        binding.booksRecyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.booksFlow.collectLatest {
                binding.progressBar.isVisible = false
                when (it) {
                    is BookState.SUCCESS -> {
                        adapter.submitList(it.books)
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
}