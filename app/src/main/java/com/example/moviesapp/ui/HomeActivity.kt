package com.example.moviesapp.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.utils.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : AppCompatActivity(), IHome {
    lateinit var movieListAdapter: MovieListAdapter
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val viewModelFactory = MovieViewModelFactory()
        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        binding.homeViewModel = homeViewModel
        homeViewModel.callback = this

        init()
    }

    fun init() {
        val orientation = resources.configuration.orientation
        /**
         * Check current orientation and set the span count.
         */
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            movieList.layoutManager = GridLayoutManager(this, 3)
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            movieList.layoutManager = GridLayoutManager(this, 7)
        }
        movieList.addItemDecoration(ItemOffsetDecoration())
        movieListAdapter = MovieListAdapter()

        //get viewmodel
        homeViewModel.setupPaging()
        homeViewModel.moviePagedList?.observe(this, Observer {
            movieListAdapter.submitList(it)
        })
        movieList.adapter = movieListAdapter

        homeViewModel.searchKeyword.observe(this, Observer {
            if(it.isNullOrEmpty()) {
                //clear search result and go back to page 1
                homeViewModel.startSearch("")
            } else if(it.length > 2) {
                //if the query length is 3 or more, filter the list accordingly
                homeViewModel.startSearch(it.trim())
            }
        })

        toolbarTitle.text = "Romantic Comedy"
    }

    override fun onBackPressed() {
        /**
         * Check if the search mode is ON. If so toggle the search box visibility
         */

        if (titleLayout.visibility == View.VISIBLE) {
            super.onBackPressed()
        } else {
            searchCloseClicked()
        }
    }

    override fun exitApp() {
        finish()
    }

    override fun searchButtonClicked() {
        titleLayout.visibility = View.GONE
        searchLayout.visibility = View.VISIBLE
        searchBox.requestFocus()
    }

    override fun searchCloseClicked() {
        titleLayout.visibility = View.VISIBLE
        searchLayout.visibility = View.GONE
        searchBox.setText("")
        homeViewModel.searchKeyword.value = ""
    }
}