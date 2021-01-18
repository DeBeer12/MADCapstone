package com.example.madcapstone.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madcapstone.R
import com.example.madcapstone.databinding.BandItemBinding

class BandAdapter(private val bands: List<Band>) :
    RecyclerView.Adapter<BandAdapter.ViewHolder>() {
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val binding = BandItemBinding.bind(itemView)

            fun databind(band: Band) {
                binding.tvBand.text = band.itemText;
                binding.tvStage.text = band.stageText;
                binding.tvTime.text = band.timeText
            }
        }

        /**
         * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
         */
        @Override
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.band_item, parent, false)
            )
        }

        /**
         * Returns the size of the list
         */
        @Override
        override fun getItemCount(): Int {
            return bands.size
        }

        /**
         * Called by RecyclerView to display the data at the specified position.
         */
        @Override
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.databind(bands[position])
        }
}