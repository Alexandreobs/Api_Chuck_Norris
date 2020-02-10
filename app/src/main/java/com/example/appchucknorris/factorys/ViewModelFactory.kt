package com.example.appchucknorris.factorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appchucknorris.model.repository.ChuckRepository
import com.example.appchucknorris.viewmodel.ChuckViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChuckViewModel(ChuckRepository()) as T
    }
}