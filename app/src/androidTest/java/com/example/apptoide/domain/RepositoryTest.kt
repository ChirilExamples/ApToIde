package com.example.apptoide.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.apptoide.data.remote.ServiceATI
import com.example.apptoide.data.structure.AppInf
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


internal class RepositoryTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutionRule: TestRule = InstantTaskExecutorRule()

    private val api: ServiceATI = mockk(relaxed = true)

    lateinit var repo: Repository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repo = Repository(api)
    }

//    @Test
//    fun `get character should return livedata of character API data`() {
//        repo.getAllData().first()
//
//        verify { charactersObserver.onChanged(any()) }
//    }



    fun previewItemsData() = AppInf(
        12,
        "url.big.picture",
        "url.small.picture",
        3, "appname",
        2.2,
        156,
        6
    )

}