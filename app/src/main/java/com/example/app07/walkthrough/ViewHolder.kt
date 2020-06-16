package com.example.app07.walkthrough

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.app07.R
import com.example.app07.walkthrough.ViewItem

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textHead = view.findViewById<TextView>(R.id.walkthrough_title)
    private val textTitle = view.findViewById<TextView>(R.id.walkthrough_text)
    private val imageIcon = view.findViewById<ImageView>(R.id.walkthrough_icon)

    fun bind(viewItem: ViewItem) {
        textHead.text = viewItem.head
        textTitle.text = viewItem.title
        imageIcon.setImageResource(viewItem.iconID)
    }
}