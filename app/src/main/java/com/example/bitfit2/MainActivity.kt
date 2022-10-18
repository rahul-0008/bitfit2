package com.example.bitfit2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit2.*
import com.example.bitfit2.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private var temp = 0
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment: Fragment
    private fun refresh(articleListFragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.detach(articleListFragment)
        fragmentTransaction.attach(articleListFragment)
        fragmentTransaction.commit()
    }
    private fun replaceFragment(articleListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.article_frame_layout, articleListFragment)
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val fragmentManager: FragmentManager = supportFragmentManager
        // define your fragments here
        val food: Fragment = FoodListFragment()
        val stat:Fragment = StatListFragment()
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_log -> fragment = food
                R.id.nav_dash -> fragment = stat
            }
            replaceFragment(fragment)
            true
        }
        fun dep(){
            if (temp>=1){
                Log.i("after",fragment.toString())
                refresh(fragment)
            }}
        val button = findViewById<Button>(R.id.add_new_food)
        button.setOnClickListener{
            temp+=1
            val intent = Intent(this, Detail::class.java)
            this.startActivity(intent)

        }
        bottomNavigationView.selectedItemId = R.id.nav_log

//        Log.i("op",foods.last().item.toString())
    }
}