package com.ram.codingtest.utilis

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(context).load(url).into(this)
}

fun ProgressBar.setVisibility () {
    this.visibility = View.VISIBLE
}

fun ProgressBar.setVisibilityGone () {
    this.visibility = View.GONE
}