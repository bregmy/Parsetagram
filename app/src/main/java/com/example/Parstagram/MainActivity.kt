package com.example.Parstagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.Parstagram.Fragments.ComposeFragment
import com.example.Parstagram.Fragments.FeedFragment
import com.example.Parstagram.Fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private var progressBar: ProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager


        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener { item ->
            var fragmentToShow: Fragment? = null
            when (item.itemId) {
                R.id.action_home -> {
                    fragmentToShow = FeedFragment()

                }
                R.id.action_compose -> {
                    fragmentToShow = ComposeFragment()


                }
                R.id.action_profile -> {
                    fragmentToShow= ProfileFragment()
                }
            }
            if (fragmentToShow != null) {
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow)
                    .commit()
            }
            true
        }

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setSelectedItemId(R.id.action_home)

        //  queryPosts()
    }


















    companion object{
        const val TAG ="MainActivity"
    }
}