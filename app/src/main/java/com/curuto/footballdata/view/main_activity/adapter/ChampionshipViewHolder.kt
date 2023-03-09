package com.curuto.footballdata.view.main_activity.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.curuto.footballdata.R
import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.databinding.RowItemChampionshipBinding
import com.curuto.footballdata.view.custom.OnRowClicked

class ChampionshipViewHolder(itemView: RowItemChampionshipBinding,
                             private val downloadDataClickListener: OnRowClicked,
                             private val onItemClicked: OnRowClicked) :
                                RecyclerView.ViewHolder(itemView.root), View.OnClickListener {

    val tvChampionship = itemView.tvChampionshipName


    init {
        itemView.ibChampionshipDownloadData.setOnClickListener(this)
        itemView.llcChampionshipAdapterRoot.setOnClickListener(this)
    }

    fun bind(championship: Championship){
        tvChampionship.text = championship.name
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ib_championship_download_data -> downloadDataClickListener.onPositionClicked(adapterPosition)
            R.id.llc_championship_adapter_root -> onItemClicked.onPositionClicked(adapterPosition)
        }
    }
}