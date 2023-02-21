package com.curuto.footballdata

import com.curuto.footballdata.repository.RealmModule
import com.curuto.footballdata.viewModel.ChampionshipViewModel
import dagger.Component

@Component(modules = [RealmModule::class])
interface RealmComponent {

    fun inject(championshipViewModel: ChampionshipViewModel)
}