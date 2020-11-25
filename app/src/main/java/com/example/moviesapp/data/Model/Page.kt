package com.example.moviesapp.data.Model

import com.google.gson.annotations.SerializedName

data class Page(
    @SerializedName("title")
    val title: String,

    @SerializedName("total-content-items")
    val total_content_items: String,

    @SerializedName("page-num")
    val page_num: String,

    @SerializedName("page-size")
    val page_size: String,

    @SerializedName("content-items")
    val content_items: ContentItems
)