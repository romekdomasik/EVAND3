package com.technipixl.exo1

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.technipixl.exo1.databinding.CharCellLayoutBinding

class MarvelCharViewHolder(
    private var viewBinding: CharCellLayoutBinding,
    val onItemClick: (Marvel) -> Unit
): RecyclerView.ViewHolder(viewBinding.root) {

    fun bind (marvelChar: Marvel){
        viewBinding.heroName.text = marvelChar.name
        setupImage(marvelChar.thumbnail.path+"."+marvelChar.thumbnail.extension)
        itemView.setOnClickListener {
            onItemClick(marvelChar)

        }


    }

    private fun setupImage(url: String) {
        Picasso.get()
            .load(url)
            .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
            .fit()
            .centerInside()
            .into(viewBinding.heroImg)
    }
}