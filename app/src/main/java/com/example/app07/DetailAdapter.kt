package com.example.app07

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app07.model.Detail
import kotlinx.android.synthetic.main.item_catalogview.view.cardViewContainer
import kotlinx.android.synthetic.main.item_detail.view.*


class DatailAdapter(private val dataList: List<Detail>) : RecyclerView.Adapter<DetailHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false)
        return DetailHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: DetailHolder, position: Int) {
        val viewItem = dataList[position]
        holder.bind(viewItem, position)
    }

}


class DetailHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textTitle = view.tvDetailItem
    private val imageView = view.ivDetailItem
    private val cardView = view.cardViewContainer
    private val context = view.context


    fun bind(item: Detail,  position: Int) {
        textTitle.text = item.Title
        var defaultImage = "https://cdn.pixabay.com/photo/2019/10/27/19/47/eiffel-tower-4582649_960_720.png"
        if (item.ImageURL != null) defaultImage =item.ImageURL
        Glide.with(context).load(defaultImage).into(imageView)
        if (position == 1)  cardView.setCardBackgroundColor(Color.parseColor("#F179A7"))
        else if (position == 2)  cardView.setCardBackgroundColor(Color.parseColor("#A9E5C1"))
        else if (position == 3)  cardView.setCardBackgroundColor(Color.parseColor("#A8DEE8"))

    }


}