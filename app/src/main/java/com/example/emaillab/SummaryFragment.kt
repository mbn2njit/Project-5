package com.example.emaillab

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.codepath.articlesearch.ItemApplication
import kotlinx.coroutines.launch
import kotlin.math.log

/**
 * A simple [Fragment] subclass.
 * Use the [SummaryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SummaryFragment : Fragment() {
    private var items = mutableListOf<DisplayItem>()
    private var avgCal: Double = 0.0
    private var maxCal: Double = 0.0
    private var tolProtein: Double = 0.0
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_summary, container, false)

        return view
    }

    companion object {
        fun newInstance(): SummaryFragment {
            return SummaryFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //welcome to the spaghetti zone
        items = fetchItems()

        avgCal = 0.0
        maxCal = 0.0
        tolProtein = 0.0
        count = 0
        Log.d("onVC", "Test log")
        for(item in items)
        {
            Log.d("newTag", item.toString())
            avgCal += item.moneyStr.toDouble()
            if(maxCal < item.moneyStr.toDouble())
                maxCal = item.moneyStr.toDouble()
            tolProtein += item.linkStr.toDouble()
            count++
        }
        avgCal/=count
        val AvCalTv = view.findViewById<TextView>(R.id.avgCal)
        val MaxCalTv = view.findViewById<TextView>(R.id.maxCal)
        val TolProtTv = view.findViewById<TextView>(R.id.tolProtein)

        AvCalTv.text = avgCal.toString()
        MaxCalTv.text = maxCal.toString()
        TolProtTv.text = tolProtein.toString()
    }

    private fun fetchItems(): MutableList<DisplayItem> {
        lifecycleScope.launch {
            (requireActivity().application as ItemApplication).db.itemDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayItem(
                        entity.name,
                        entity.price.toString(),
                        entity.url.toString()
                    )
                }.also { mappedList ->
                    items.clear()
                    items.addAll(mappedList)
                }
            }
        }
        return items
    }
}