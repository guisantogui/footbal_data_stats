package com.curuto.footballdata.view.championship_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.curuto.footballdata.databinding.RowItemTeamBinding
import com.curuto.footballdata.model.Team
import com.curuto.footballdata.view.championship_detail.view_model.ChampionshipDetailViewModel
import dagger.Module
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import javax.inject.Inject

open class TeamListAdapter
@Inject constructor(var teamList: OrderedRealmCollection<Team>) :
    RealmRecyclerViewAdapter<Team, TeamViewHolder>(teamList, true) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = RowItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teamList[position])
    }
}

@Module
open class TeamListModule(){

    fun getTeamListAdapter(): TeamListAdapter{
        val viewModel = ChampionshipDetailViewModel()

        return TeamListAdapter(viewModel.getAllTeams())
    }
}