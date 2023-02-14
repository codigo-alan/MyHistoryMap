package com.example.grafitismap.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grafitismap.R
import com.example.grafitismap.databinding.ItemMarkerBinding
import com.example.grafitismap.models.MarkerModel

class MarkerAdapter(private var markerModels: List<MarkerModel>, private var listener: OnClickListener)
    : RecyclerView.Adapter<MarkerAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemMarkerBinding.bind(view)

        fun setListener(markerModel: MarkerModel){
            binding.root.setOnClickListener {
                listener.onClick(markerModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_marker, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  markerModels.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val marker = markerModels[position]

        with(holder){
            setListener(marker)
            binding.itemNameTv.text = marker.name
            binding.itemCategoryTv.text = marker.category
        }
    }

    fun setMarkers(newListMarkerModels: List<MarkerModel>){
        markerModels = newListMarkerModels
        notifyDataSetChanged()
    }

}