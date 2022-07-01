package com.tista.cleanmoviebrowser.feature.movie.presentation.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.tista.cleanmoviebrowser.feature.movie.R
import com.tista.cleanmoviebrowser.feature.movie.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {
    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = MovieDetailFragmentArgs.fromBundle(requireArguments()).movie
        binding.title.text = movie.title
        binding.releaseDate.text=  android.text.format.DateFormat.format("yyyy", movie.releaseDate)
        binding.rating.text = "${movie.voteAverage ?: 0}"
        binding.description.text = movie.overview
        Glide.with(requireActivity())
            .load(movie.backdropImageUrl)
            .error(R.drawable.ic_broken_image)
//            .transform(RoundedCorners(20))
            .into(binding.ivBackdrop)
    }
}