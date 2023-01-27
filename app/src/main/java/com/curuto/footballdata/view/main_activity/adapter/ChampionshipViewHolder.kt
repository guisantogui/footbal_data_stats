package com.curuto.footballdata.view.main_activity.adapter

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.databinding.RowItemChampionshipBinding


class ChampionshipViewHolder(itemView: RowItemChampionshipBinding) : RecyclerView.ViewHolder(itemView.root) {

    val tvChampionship = itemView.tvChampionshipName
    val ibDownloadData = itemView.ibDownloadData

    fun bind(championship: Championship){

        tvChampionship.text = championship.name
        ibDownloadData.setOnClickListener {
            Toast.makeText(itemView.context, "CLICKED", Toast.LENGTH_SHORT).show()
        }
    }
}