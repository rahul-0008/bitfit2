package com.example.bitfit2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddInto() : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_add__into, container, false)
        val sql = AppDatabase2.getInstance(view.context).foodDAO()
        var item_t = view.findViewById<EditText>(R.id.food_name)
        var amt = view.findViewById<EditText>(R.id.claorie_count)
        var t1 = ""
        var t2 : Float
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sql = AppDatabase2.getInstance(view.context).foodDAO()
        var item_t = view.findViewById<EditText>(R.id.food_name)
        var amt = view.findViewById<EditText>(R.id.claorie_count)
        val but = view.findViewById<Button>(R.id.record)
        but.setOnClickListener {
            var t1 = item_t.text.toString()
            var t2 = amt.text.toString().toFloat()
            lifecycleScope.launch(Dispatchers.IO) {
                sql.insert(
                    FoodEntity(
                        item = t1,
                        calories = t2
                    )
                )
            }
        }
        
    }

    companion object {

        fun newInstance(): AddInto {
            return AddInto()
        }

    }
}