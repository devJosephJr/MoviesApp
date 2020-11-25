package com.example.moviesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.data.Model.Content
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieListAdapter : PagedListAdapter<Content, MovieListAdapter.MovieViewHolder>(
    diffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)

        movie?.let {
            var image: Int? = null
            when(it.poster_image) {
                "poster1.jpg" -> image = R.drawable.poster1
                "poster2.jpg" -> image = R.drawable.poster2
                "poster3.jpg" -> image = R.drawable.poster3
                "poster4.jpg" -> image = R.drawable.poster4
                "poster5.jpg" -> image = R.drawable.poster5
                "poster6.jpg" -> image = R.drawable.poster6
                "poster7.jpg" -> image = R.drawable.poster7
                "poster8.jpg" -> image = R.drawable.poster8
                "poster9.jpg" -> image = R.drawable.poster9
                "posterthatismissing.jpg" -> image = R.drawable.placeholder_for_missing_posters
            }
            Glide.with(holder.itemView)
                .load(image)
                .into(holder.itemView.movieImage)

            holder.itemView.movieName.setText(it.name)
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Content>() {
            override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
                return oldItem == newItem
            }
        }
    }
}
