package com.curuto.footballdata.repository.realm

import com.curuto.footballdata.services.CSVParseWorker
import com.curuto.footballdata.viewModel.ChampionshipViewModel
import dagger.Component

@Component(modules = [RealmModule::class])
interface RealmComponent {

    fun inject(championshipViewModel: ChampionshipViewModel)
    fun inject(csvParseWorker: CSVParseWorker)
}