package com.curuto.footballdata.view.season_activity.adapter


import androidx.recyclerview.widget.RecyclerView
import com.curuto.footballdata.databinding.RowItemSeasonBinding
import com.curuto.footballdata.model.Season

class SeasonViewHolder(private var binding: RowItemSeasonBinding): RecyclerView.ViewHolder(binding.root) {


    fun bind(season: Season){
        binding.tvSeasonName.text = season.period
    }
}