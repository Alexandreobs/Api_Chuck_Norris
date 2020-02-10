package com.example.appchucknorris

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.appchucknorris.model.repository.ChuckRepository
import com.example.appchucknorris.viewmodel.ChuckViewModelPiadas
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ChuckRepository

    @Mock
    lateinit var getAllPiadas: ChuckViewModelPiadas

    @Mock
    lateinit var viewModel: ChuckViewModelPiadas

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = (getAllPiadas)
    }

    @Test
    fun getAllPiadasTest() {

    }

}

