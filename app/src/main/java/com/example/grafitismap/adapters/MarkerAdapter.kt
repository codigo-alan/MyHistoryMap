package com.example.grafitismap.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grafitismap.R
import com.example.grafitismap.databinding.ItemMarkerBinding
import com.example.grafitismap.models.Marker

class MarkerAdapter(private var markers: List<Marker>, private var listener: OnClickListener)
    : RecyclerView.Adapter<MarkerAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemMarkerBinding.bind(view)

        fun setListener(marker: Marker){
            binding.root.setOnClickListener {
                listener.onClick(marker)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_marker, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  markers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val marker = markers[position]

        with(holder){
            setListener(marker)
            binding.itemNameTv.text = marker.name
        }
    }

    fun setMarkers(newListMarkers: List<Marker>){
        markers = newListMarkers
        notifyDataSetChanged()
    }

}