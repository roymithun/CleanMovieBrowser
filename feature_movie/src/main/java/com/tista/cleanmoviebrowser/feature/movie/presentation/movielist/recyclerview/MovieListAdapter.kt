package com.tista.cleanmoviebrowser.feature.movie.presentation.movielist.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.tista.cleanmoviebrowser.feature.movie.R
import com.tista.cleanmoviebrowser.feature.movie.databinding.MovieItemGridBinding
import com.tista.cleanmoviebrowser.feature.movie.domain.model.Movie

class MovieListAdapter(private val itemClickListener: OnItemClickListener) :
    ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(MovieDiffCallback()) {

    interface OnItemClickListener {
        fun onMovieItemClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            MovieItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie, itemClickListener)
    }

    class MovieViewHolder(private val binding: MovieItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, itemClickListener: OnItemClickListener) {
            binding.movie = movie
            binding.clickListener = itemClickListener
            binding.executePendingBindings()
        }
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}

@BindingAdapter("posterImage")
fun posterImage(iv: ImageView, url: String) {
    Glide.with(iv.context)
        .load(url)
        .error(R.drawable.ic_broken_image)
        .transform(CenterInside(), RoundedCorners(30))
        .into(iv)
}