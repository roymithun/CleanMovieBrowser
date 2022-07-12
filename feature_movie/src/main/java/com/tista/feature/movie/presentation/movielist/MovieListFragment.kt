package com.tista.feature.movie.presentation.movielist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.tista.feature.movie.MoviesFeatureModule
import com.tista.feature.movie.R
import com.tista.feature.movie.databinding.FragmentMovieListBinding
import com.tista.feature.movie.domain.model.Movie
import com.tista.feature.movie.presentation.movielist.recyclerview.MovieListAdapter
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import retrofit2.Retrofit

class MovieListFragment : Fragment(), MovieListAdapter.OnItemClickListener {
    private val viewModel: MovieListViewModel by inject()
    private val retrofit: Retrofit by inject()
    private lateinit var binding: FragmentMovieListBinding
    private val movieAdapter = MovieListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_movie_list, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MoviesFeatureModule.init()

        Log.d("TAG", "retrofit $retrofit")
        binding.rvMovies.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = movieAdapter
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewStateFlow.collect {
                movieAdapter.submitList(it.movies)
            }
        }
        binding.viewModel = viewModel
        viewModel.loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        MoviesFeatureModule.deInit()
    }

    override fun onMovieItemClick(
        movie: Movie
    ) {
        viewModel.navigateToMovieDetails(movie)
    }
}