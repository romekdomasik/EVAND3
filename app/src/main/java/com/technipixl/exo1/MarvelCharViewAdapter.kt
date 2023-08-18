package com.technipixl.exo1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.technipixl.exo1.databinding.CharCellLayoutBinding

class MarvelCharViewAdapter(private var data: List<Marvel>, private val onItemClick: (Marvel) -> Unit
): RecyclerView.Adapter<MarvelCharViewHolder>() {
    private lateinit var binding: CharCellLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharViewHolder {
        return MarvelCharViewHolder(
        CharCellLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false),
        onItemClick )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MarvelCharViewHolder, position: Int) {
        holder.bind(data[position])
    }


}