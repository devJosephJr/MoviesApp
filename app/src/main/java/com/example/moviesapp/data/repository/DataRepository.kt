package com.example.moviesapp.data.repository

import com.example.moviesapp.data.Model.MoviesModel
import com.example.moviesapp.data.Model.Content
import com.example.moviesapp.utils.MovieDataUtil
import com.google.gson.Gson
import kotlin.collections.ArrayList

class DataRepository {
    fun getDataForPage(pageNum: Int, query: String?): MoviesModel {
        val data: MoviesModel?
        when (pageNum) {
            1 -> {
                data =
                    Gson().fromJson<MoviesModel>(MovieDataUtil.page1Data, MoviesModel::class.java)
            }

            2 -> {
                data =
                    Gson().fromJson<MoviesModel>(MovieDataUtil.page2Data, MoviesModel::class.java)
            }

            3 -> {
                data =
                    Gson().fromJson<MoviesModel>(MovieDataUtil.page3Data, MoviesModel::class.java)
            }

            else -> data = null
        }

        return filterData(data!!, query)
    }

    private fun filterData(data: MoviesModel, query: String?): MoviesModel {
        println("## Key word = ${query}")
        if (query.isNullOrEmpty()) {
            return data
        } else {
            val movieList = data.page.content_items.content
            val filteredList = ArrayList<Content>()
            for (movie in movieList) {
                if (movie.name.startsWith(query, true)) {
                    if (filteredList.contains(movie)) {
                        /**
                         * The following condition will remove duplicate contents from the
                         * search result. An item in the list is considered as duplicate if
                         * they have the same name (case sensitive) and same poster image.
                         */
                        val index = filteredList.indexOf(movie)
                        val exisitngData = filteredList.get(index)
                        if (exisitngData.name.equals(movie.name) &&
                            exisitngData.poster_image.equals(movie.poster_image)) {
                            continue
                        }
                    }
                    filteredList.add(movie)
                }
            }
            data.page.content_items.content = filteredList
            return data
        }
    }
}