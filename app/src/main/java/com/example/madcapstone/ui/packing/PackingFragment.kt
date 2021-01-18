package com.example.madcapstone.ui.packing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcapstone.R
import com.example.madcapstone.db.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PackingFragment : Fragment() {

    private lateinit var dashboardViewModel: PackingViewModel
    private val items = arrayListOf<Item>()
    private val itemAdapter = ItemAdapter(items)
    private lateinit var itemRepository: ItemRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(PackingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_packing, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.rvItems)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = this.itemAdapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        itemRepository = ItemRepository(requireContext())
        getItemsFromDatabase()
    }

    private fun getItemsFromDatabase() {
        mainScope.launch {
            val storedItems = withContext(Dispatchers.IO) {
                itemRepository.getAllItems()
            }
            if(storedItems.isEmpty()){
                itemRepository.insertItem(Item(
                    itemText = "Raincoat",
                ))
                itemRepository.insertItem(Item(
                    itemText = "Sunscreen",
                ))
                itemRepository.insertItem(Item(
                    itemText = "Food",
                ))
                items.addAll(storedItems)
                itemAdapter.notifyDataSetChanged()
            } else {
                items.clear()
                items.addAll(storedItems)
                itemAdapter.notifyDataSetChanged()
            }
        }
    }
}