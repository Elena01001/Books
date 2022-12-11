package ru.netology.books.app.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import ru.netology.books.R
import ru.netology.books.app.presentation.ui.FeedFragment.Companion.FEED
import ru.netology.books.databinding.BookDetailsFragmentBinding
import ru.netology.books.domain.model.Book

class BookDetailsFragment : Fragment() {

    private var _binding: BookDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<BookDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BookDetailsFragmentBinding.inflate(inflater, container, false)

        bindScreen(args.bookCard)

        return binding.root
    }

    private fun bindScreen(book: Book) {
        with(binding) {
            title.text = book.title
            author.text = book.author
            category.text = book.category
            publishingDate.text = book.publicationDate
            description.text = book.description
            Glide.with(image)
                .load(book.image)
                .placeholder(R.drawable.ic_book)
                .error(R.drawable.ic_book)
                .into(image)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (args.comeback == FEED)
            findNavController().popBackStack(R.id.feedFragment, false)
        else
            findNavController().popBackStack(R.id.searchByCategoryFragment, false)
    }
}