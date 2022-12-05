package ru.netology.books.app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.books.R
import ru.netology.books.databinding.BookItemBinding
import ru.netology.books.domain.model.Book

class BooksAdapter(
    private val interactionListener: BookInteractionListener
) : ListAdapter<Book, BooksAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BookItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    class ViewHolder(
        private val binding: BookItemBinding,
        listener: BookInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var book: Book

        init {
            binding.image.setOnClickListener { listener.onBookCardClicked(book) }
            binding.title.setOnClickListener { listener.onBookCardClicked(book) }
            binding.author.setOnClickListener { listener.onBookCardClicked(book) }
            binding.publishingDate.setOnClickListener { listener.onBookCardClicked(book) }
        }

        fun bind(book: Book) {
            this.book = book
            with(binding) {
                title.text = book.title
                author.text = book.author
                publishingDate.text = book.publicationDate
                image.setImageResource(R.drawable.ic_book)
            }
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Book, newItem: Book) =
            oldItem == newItem
    }
}