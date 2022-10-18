package com.example.bitfit2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.bitfit2.FoodAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class Detail: AppCompatActivity()  {
    private val foods = mutableListOf<FoodEntity>()
    private lateinit var foodsRecyclerView: RecyclerView
    private lateinit var foodAdapter : FoodAdapter
    private fun refresh(articleListFragment: Fragment) : Boolean{
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.detach(articleListFragment)
        fragmentTransaction.attach(articleListFragment)
        fragmentTransaction.commit()
        return true
    }
    private fun help(view : View) : String{
        val sql = AppDatabase2.getInstance(view.context).foodDAO()
        var avg = view.findViewById<TextView>(R.id.avg)
        var max = view.findViewById<TextView>(R.id.max)
        var min = view.findViewById<TextView>(R.id.min)

        lifecycleScope.launch(Dispatchers.IO){
            avg.text = sql.avg().toString()
        }
        lifecycleScope.launch(Dispatchers.IO){
            min.text = sql.min().toString()
        }
        lifecycleScope.launch(Dispatchers.IO){
            max.text =sql.max().toString()
        }
        return min.text.toString()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_item)
        var item_t = findViewById<EditText>(R.id.food_name)
        var amt = findViewById<EditText>(R.id.claorie_count)

        var t1 = ""
        var t2 : Float
        foodAdapter = FoodAdapter(this, foods)
        val but = findViewById<Button>(R.id.record)
        but.setOnClickListener {
            t1 = item_t.text.toString()
            t2 = amt.text.toString().toFloat()
            lifecycleScope.launch(Dispatchers.IO) {
                (application as ArticleApplication2).db.foodDAO().insert(
                    FoodEntity(
                        item = t1,
                        calories = t2
                    )
                )
            }
            finish()
        }


    }

}