package com.example.madcapstone.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madcapstone.R
import com.example.madcapstone.databinding.ItemItemBinding

class ItemAdapter(private val items: List<Item>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val binding = ItemItemBinding.bind(itemView)

            fun databind(item: Item) {
                binding.cbItem.text = item.itemText
            }
        }

        /**
         * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
         */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
            )
        }

        /**
         * Returns the size of the list
         */
        override fun getItemCount(): Int {
            return items.size
        }

        /**
         * Called by RecyclerView to display the data at the specified position.
         */
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.databind(items[position])
        }
}