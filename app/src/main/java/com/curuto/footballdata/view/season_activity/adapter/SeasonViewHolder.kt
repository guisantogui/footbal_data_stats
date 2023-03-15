package com.curuto.footballdata.view.season_activity.adapter


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.curuto.footballdata.R
import com.curuto.footballdata.databinding.RowItemSeasonBinding
import com.curuto.footballdata.model.Season
import com.curuto.footballdata.view.custom.OnRowClicked

class SeasonViewHolder(private var binding: RowItemSeasonBinding,
                       private var onDowloadDataClicked: OnRowClicked):

                        RecyclerView.ViewHolder(binding.root),  View.OnClickListener {


    fun bind(season: Season){
        binding.tvSeasonName.text = season.period
        binding.ibSeasonDownloadData.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.ib_season_download_data -> onDowloadDataClicked.onPositionClicked(adapterPosition)
        }
    }
}