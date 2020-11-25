package com.example.moviesapp.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.moviesapp.data.Model.Content
import com.example.moviesapp.data.dataSource.MovieDataSourceFactory


class HomeViewModel(private val movieDataSourceFactory: MovieDataSourceFactory) :
    ViewModel() {

    var moviePagedList: LiveData<PagedList<Content>>? = null
    var movieDataSource: LiveData<PageKeyedDataSource<Int, Content>>? = null

    lateinit var callback: IHome
    var searchKeyword =  MutableLiveData<String>()

    fun setupPaging() {
        movieDataSource = movieDataSourceFactory.movieLiveData
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()
        moviePagedList = LivePagedListBuilder(movieDataSourceFactory, config).build()
    }

    fun onBackButtonClicked(view: View) {
        callback.exitApp()
    }

    fun onSearchButtonClicked(view: View) {
        callback.searchButtonClicked()
    }

    fun onCloseSearchClicked(view: View) {
        callback.searchCloseClicked()
    }

    fun startSearch(keyword: String) {
        movieDataSource?.let {
            movieDataSourceFactory.search(keyword)
            it.value?.invalidate()
        }
    }
}