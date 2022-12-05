package ru.netology.books.app.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import ru.netology.books.databinding.FeedFragmentBinding

class SearchByCategoryFragment : Fragment() {

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
        binding.searchText.hint = "Введите категорию книг"

        binding.searchButton.setOnClickListener {
            searchByCategory()
        }

        return binding.root
    }

    private fun searchByCategory() {
        TODO("Not yet implemented")
    }
}