package com.tista.cleanmoviebrowser.feature.movie.presentation.movielist

import androidx.lifecycle.viewModelScope
import com.tista.cleanmoviebrowser.base.presentation.navigation.NavManager
import com.tista.cleanmoviebrowser.base.presentation.viewmodel.BaseAction
import com.tista.cleanmoviebrowser.base.presentation.viewmodel.BaseViewModel
import com.tista.cleanmoviebrowser.base.presentation.viewmodel.BaseViewState
import com.tista.cleanmoviebrowser.feature.movie.domain.model.Movie
import com.tista.cleanmoviebrowser.feature.movie.domain.result.Result
import com.tista.cleanmoviebrowser.feature.movie.domain.usecase.MoviesListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

internal class MovieListViewModel(
    private val navManager: NavManager,
    private val movieListUseCase: MoviesListUseCase
) :
    BaseViewModel<MovieListViewModel.ViewState, MovieListViewModel.Action>(
        ViewState()
    ) {

    override fun onLoadData() {
        // reset state
        sendAction(Action.MovieListLoadingIdle)
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            movieListUseCase.execute(Unit).collect { result ->
                val action = when (result) {
                    is Result.Success ->
                        if (result.data == null) {
                            Action.MovieListLoadingFailure
                        } else {
                            Timber.d("$result.data")
                            Action.MovieListLoadingSuccess(result.data)
                        }
                    else ->
                        Action.MovieListLoadingFailure
                }
                sendAction(action)
            }
        }
    }

    data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val movies: List<Movie> = listOf()
    ) : BaseViewState

    internal sealed interface Action : BaseAction {
        class MovieListLoadingSuccess(val movies: List<Movie>) : Action
        object MovieListLoadingFailure : Action
        object MovieListLoadingIdle : Action
    }

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.MovieListLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            movies = viewAction.movies
        )
        is Action.MovieListLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            movies = listOf()
        )
        else -> state.copy(
            isLoading = true,
            isError = false,
            movies = listOf()
        )
    }


    fun navigateToMovieDetails(movie: Movie) {
        val navDirections = MovieListFragmentDirections.actionMovieListToDetailFragment(movie)
        navManager.navigate(navDirections)
    }
}