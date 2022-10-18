package com.example.bitfit2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [FoodListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodListFragment() : Fragment() {
    private val foods = mutableListOf<FoodEntity>()
    private lateinit var foodsRecyclerView: RecyclerView
    private lateinit var foodAdapter : FoodAdapter
    private lateinit var sql : FoodDAO2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_food_list, container, false)
        val sql = AppDatabase2.getInstance(view.context).foodDAO()
        foodsRecyclerView = view.findViewById<RecyclerView>(R.id.items)
        foodAdapter = FoodAdapter(view.context, foods)
        foodsRecyclerView.adapter = foodAdapter
        foodsRecyclerView.layoutManager = LinearLayoutManager(view.context).also {
            val dividerItemDecoration = DividerItemDecoration(view.context, it.orientation)
            foodsRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        lifecycleScope.launch{
            sql.getAll().collect { databaseList ->
                databaseList.map { entity ->
                    FoodEntity(
                        entity.id,
                        entity.item,
                        entity.calories
                    )
                }.also { mappedList ->
                    foods.clear()
                    foods.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()
                }
            }
        }

        return view

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreate
        val sql = AppDatabase2.getInstance(view.context).foodDAO()
        lifecycleScope.launch{
           sql.getAll().collect { databaseList ->
                databaseList.map { entity ->
                    FoodEntity(
                        entity.id,
                        entity.item,
                        entity.calories
                    )
                }.also { mappedList ->
                    foods.clear()
                    foods.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()
                }
            }
        }

    }

}