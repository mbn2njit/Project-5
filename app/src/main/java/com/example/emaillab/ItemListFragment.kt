package com.example.emaillab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.ItemApplication
import kotlinx.coroutines.launch

class ItemListFragment : Fragment() {
    private var items = mutableListOf<DisplayItem>()
    private lateinit var emailsRV: RecyclerView
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        //var items : MutableList<DisplayItem> = ArrayList()
        val layoutManager = LinearLayoutManager(context)
        emailsRV = view.findViewById(R.id.item_recycler_view)
        emailsRV.layoutManager = layoutManager
        emailsRV.setHasFixedSize(true)
        adapter = ItemAdapter(items)
        emailsRV.adapter = adapter

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_item_list, container, false)
        return view
    }

    companion object {
        fun newInstance(): ItemListFragment {
            return ItemListFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        fetchItems()
    }

    private fun fetchItems()
    {
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
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}