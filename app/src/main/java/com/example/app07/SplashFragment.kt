package com.example.app07

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : Fragment() {

    lateinit var navController: NavController

    private val SPLASH_TIME_OUT:Long = 3000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        Handler().postDelayed({
            navController.navigate(R.id.action_splashFragment_to_walkthroughFragment)

        }, SPLASH_TIME_OUT)
    }
}
