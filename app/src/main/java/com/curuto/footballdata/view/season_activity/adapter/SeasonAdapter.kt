package com.curuto.footballdata.view.season_activity.adapter

import android.view.ViewGroup
import com.curuto.footballdata.databinding.RowItemSeasonBinding
import com.curuto.footballdata.model.Season
import com.curuto.footballdata.utils.getInflater
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class SeasonAdapter(var seasonData: OrderedRealmCollection<Season>) :
    RealmRecyclerViewAdapter<Season, SeasonViewHolder>(seasonData, true) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val rowItemSeasonBinding =
            RowItemSeasonBinding.inflate(getInflater(parent.context), parent, false)
        return SeasonViewHolder(rowItemSeasonBinding)
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {

        holder.bind(seasonData[position])
    }
}