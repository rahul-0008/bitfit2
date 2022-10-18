package com.example.bitfit2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [StatListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatListFragment : Fragment() {
    private var temp = Unit

    private fun help(view :View){
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
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_stat_list, container, false)
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
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        var btn = view.findViewById<Button>(R.id.btn)
        btn.setOnClickListener({
            lifecycleScope.launch(Dispatchers.IO) {
                sql.deleteAll()
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
            }
        })
    }

    companion object {
        fun newInstance() : StatListFragment{
            return StatListFragment()
        }
    }
}