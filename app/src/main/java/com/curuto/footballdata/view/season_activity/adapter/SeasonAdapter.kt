package com.curuto.footballdata.view.season_activity.adapter

import android.view.ViewGroup
import com.curuto.footballdata.databinding.RowItemSeasonBinding
import com.curuto.footballdata.model.Season
import com.curuto.footballdata.utils.getInflater
import com.curuto.footballdata.view.custom.OnRowClicked
import com.curuto.footballdata.view.main_activity.adapter.ChampionshipAdapter
import com.curuto.footballdata.viewModel.ChampionshipViewModel
import com.curuto.footballdata.viewModel.SeasonViewModel
import dagger.Module
import dagger.Provides
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import java.util.*
import javax.inject.Inject

open class SeasonAdapter
    @Inject constructor (var seasonData: OrderedRealmCollection<Season>,
                         val onDowloadDataClicked: OnRowClicked,
                         val onItemRowClicked: OnRowClicked) :
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

@Module
open class SeasonAdapterModule(val onDowloadDataClicked: OnRowClicked,
                               val onItemRowClicked: OnRowClicked){

    @Inject lateinit var seasonViewModel: SeasonViewModel

    @Provides
    open fun getEmptyAdapter(): SeasonAdapter {

        val seasons = seasonViewModel.getAllSeasonsByChampionship(UUID.randomUUID())
        return SeasonAdapter(seasons, onDowloadDataClicked, onItemRowClicked)
    }
}