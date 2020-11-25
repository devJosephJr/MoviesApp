package com.example.moviesapp.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemOffsetDecoration: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(20,20,20,60)
    }
}