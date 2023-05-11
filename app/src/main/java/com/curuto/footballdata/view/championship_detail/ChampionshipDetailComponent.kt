package com.curuto.footballdata.view.championship_detail

import com.curuto.footballdata.repository.realm.RealmModule
import com.curuto.footballdata.view.championship_detail.adapter.TeamListModule
import dagger.Component

@Component(modules = [RealmModule::class, TeamListModule::class])
interface ChampionshipDetailComponent {

    fun inject(championshipDetailActivity: ChampionshipDetailActivity)
}