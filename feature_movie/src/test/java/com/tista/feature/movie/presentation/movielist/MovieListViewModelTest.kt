package com.tista.feature.movie.presentation.movielist

import android.text.format.DateFormat
import com.tista.cleanmoviebrowser.base.presentation.navigation.NavManager
import com.tista.feature.movie.TestData
import com.tista.feature.movie.domain.usecase.MoviesListUseCase
import com.tista.library.testutil.InstantTaskExecutorExtension
import com.tista.library.testutil.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class MovieListViewModelTest {

    @ExperimentalCoroutinesApi
    @JvmField
    @RegisterExtension
    val mainCoroutineRule = MainCoroutineRule()

    @JvmField
    @RegisterExtension
    var instantTaskExecutorExtension = InstantTaskExecutorExtension()

    @MockK
    internal lateinit var mockMoviesListUseCase: MoviesListUseCase

    @MockK(relaxed = true)
    internal lateinit var mockNavManager: NavManager

    private lateinit var vm: MovieListViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        vm = MovieListViewModel(
            mockNavManager,
            mockMoviesListUseCase
        )
    }

    @Test
    fun `execute getPopularTvShows`() {
        // when
        vm.loadData()

        // then
        coVerify { mockMoviesListUseCase.execute(Unit) }
    }

    @Test
    fun `navigate to movie details`() {
        mockkStatic(DateFormat::class)

        every { DateFormat.format("yyyy", any<Date>()) } returns "2022-05-04"

        val navDirections =
            MovieListFragmentDirections.actionMovieListToDetailFragment(TestData.movie1)

        // when
        vm.navigateToMovieDetails(TestData.movie1)

        // then
        coVerify { mockNavManager.navigate(navDirections) }
    }
}