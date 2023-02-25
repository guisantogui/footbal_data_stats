package com.curuto.footballdata.view.main_activity.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.curuto.footballdata.R
import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.databinding.RowItemChampionshipBinding
import com.curuto.footballdata.view.custom.OnRowClicked

class ChampionshipViewHolder(itemView: RowItemChampionshipBinding, private val downloadDataClickListener: OnRowClicked) :
                                RecyclerView.ViewHolder(itemView.root), View.OnClickListener {

    val tvChampionship = itemView.tvChampionshipName


    init {
        itemView.ibDownloadData.setOnClickListener(this)
    }

    fun bind(championship: Championship){
        tvChampionship.text = championship.name + "Season: "+ championship.season
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ib_download_data -> downloadDataClickListener.onPositionClicked(adapterPosition)
        }
    }
}