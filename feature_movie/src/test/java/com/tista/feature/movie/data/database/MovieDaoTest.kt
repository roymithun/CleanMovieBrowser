package com.tista.feature.movie.data.database


import com.tista.feature.movie.TestData
import com.tista.feature.movie.data.roomTestModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Test
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.test.KoinTest
import org.koin.test.inject

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDaoTest : KoinTest {
    /*
     * Inject needed components from Koin
     */
    private val movieDatabase: MovieDatabase by inject()
    private val movieDao: MovieDao by inject()

    /**
     * Override default Koin configuration to use Room in-memory database
     */
    @Before
    fun before() {
        loadKoinModules(roomTestModule)
    }

    /**
     * Close resources
     */
    @After
    fun after() {
        movieDatabase.close()
//        closeKoin()
    }

    @Test
    suspend fun `Test movie entity save`() {
        val entities = TestData.movieEntityList

        movieDao.insertMovies(entities)

        val ids = entities.map { it.id }

        // Request one entity per id
        val requestedEntities = ids.map { movieDao.getMovie(it) }

        // compare result
        entities shouldBeEqualTo requestedEntities
    }
}