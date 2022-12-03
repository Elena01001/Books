package ru.netology.books.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.books.databinding.FeedFragmentBinding
import ru.netology.books.presentation.BookViewModel
import ru.netology.books.presentation.adapter.BooksAdapter

class FeedFragment : Fragment() {

    /*AIzaSyCdWvYMtPl9P3E37YnBzdOvwBhD2L42RF0*/

    private val viewModel: BookViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FeedFragmentBinding.inflate(layoutInflater, container, false).also { binding ->
        val adapter = BooksAdapter(viewModel)
        binding.booksRecyclerView.adapter = adapter
        /*viewModel.data.observe(viewLifecycleOwner) { books ->
            adapter.submitList(books)
        }*/
    }.root

    /*fun search() {
        val query = binding.searchText.editText?.text.toString()
    }*/
}