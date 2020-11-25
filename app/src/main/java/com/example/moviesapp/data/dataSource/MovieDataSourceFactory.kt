package com.example.moviesapp.data.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.moviesapp.data.Model.Content

class MovieDataSourceFactory : DataSource.Factory<Int, Content>() {

    val movieLiveData = MutableLiveData<PageKeyedDataSource<Int, Content>>()
    var searchQuery: String? = ""

    override fun create(): DataSource<Int, Content> {
        val movieDataSource = MovieDataSource(searchQuery)
        movieLiveData.postValue(movieDataSource)
        return movieDataSource
    }

    fun search(keyword: String) {
        searchQuery = keyword
    }
}