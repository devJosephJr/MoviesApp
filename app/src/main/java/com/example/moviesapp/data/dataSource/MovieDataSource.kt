package com.example.moviesapp.data.dataSource

import androidx.paging.PageKeyedDataSource
import com.example.moviesapp.data.Model.Content
import com.example.moviesapp.data.repository.DataRepository

class MovieDataSource(val query: String?) : PageKeyedDataSource<Int, Content>() {

    val firstPage = 1
    val repository = DataRepository()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Content>
    ) {
        val data = getData(firstPage)
        callback.onResult(data.page.content_items.content as MutableList<Content> , null, firstPage + 1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Content>) {
        val data = getData(params.key)
        val key = if (params.key > 2) {
            null
        } else {
            params.key + 1
        }
        callback.onResult(data.page.content_items.content as MutableList<Content>,key)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Content>) {
        val data = getData(params.key)
        val key = if (params.key > 1) {
            params.key - 1
        } else {
            null
        }
        callback.onResult(data.page.content_items.content as MutableList<Content>,key)
    }

    private fun getData(pageNum: Int) = repository.getDataForPage(pageNum,query)
}