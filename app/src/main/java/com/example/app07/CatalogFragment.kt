package com.example.app07

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_catalog.*

/**
 * A simple [Fragment] subclass.
 */
class CatalogFragment : Fragment() {

    private lateinit var CategoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model: WelcomeViewModel by viewModels()
        model.savedCategory.observe(viewLifecycleOwner, Observer {
            // Update UI Only
            Log.d("MY_TAG", it.toString())
            for (category in it) {
                Log.d("MY_TAG", category.toString())
                swipeRefreshLayout.isRefreshing = false

                CategoryAdapter = CategoryAdapter(it)
                recycleView.adapter = CategoryAdapter
            }
        })
        model.fetchNewCategory()

        swipeRefreshLayout.setOnRefreshListener {
            model.fetchNewCategory()
        }
    }

}
