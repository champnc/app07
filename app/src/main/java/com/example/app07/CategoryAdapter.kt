package com.example.app07

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app07.R
import com.example.app07.model.Category
import kotlinx.android.synthetic.main.item_catalogview.view.*


class CategoryAdapter(private val dataList: List<Category>) : RecyclerView.Adapter<CategoryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_catalogview, parent, false)
        return CategoryHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val viewItem = dataList[position]
        holder.bind(viewItem, position)
        holder.itemView.setOnClickListener{
            val value = Bundle()
            value.putString("nameArg", viewItem.Title.toString())
            Log.d("MY_TAG", viewItem.Title.toString())
            it.findNavController().navigate(R.id.action_catalogFragment_to_detailFragment,value)
        }

    }

}


class CategoryHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textTitle = view.tvCategoryItem
    private val imageView = view.ivCategoryItem
    private val cardView = view.cardViewContainer
    private val context = view.context

    fun bind(item: Category,  position: Int) {
        textTitle.text = item.Title
        var defaultImage = "https://cdn.pixabay.com/photo/2019/10/27/19/47/eiffel-tower-4582649_960_720.png"
        if (item.ImageURL != null) defaultImage =item.ImageURL
        Glide.with(context).load(defaultImage).into(imageView)
        if (position == 1)  cardView.setCardBackgroundColor(Color.parseColor("#F179A7"))
        else if (position == 2)  cardView.setCardBackgroundColor(Color.parseColor("#A9E5C1"))
        else if (position == 3)  cardView.setCardBackgroundColor(Color.parseColor("#A8DEE8"))

    }
}