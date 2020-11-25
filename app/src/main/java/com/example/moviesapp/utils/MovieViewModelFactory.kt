package com.example.moviesapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.data.dataSource.MovieDataSourceFactory
import com.example.moviesapp.ui.HomeViewModel

class MovieViewModelFactory: ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(MovieDataSourceFactory()) as T
            }

            else -> throw IllegalArgumentException("Invalid class name")
        }
    }
}