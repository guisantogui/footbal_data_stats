package com.curuto.footballdata.view.championship_detail

import com.curuto.footballdata.repository.realm.RealmModule
import dagger.Component

@Component(modules = [RealmModule::class])
interface ChampionshipDetailComponent {

    fun inject(championshipDetailActivity: ChampionshipDetailActivity)
}