package com.curuto.footballdata.repository.realm

import com.curuto.footballdata.FootballDataApplication
import com.curuto.footballdata.services.csvParser.CSVParseWorker
import com.curuto.footballdata.viewModel.ChampionshipViewModel
import com.curuto.footballdata.viewModel.SeasonViewModel
import dagger.Component

@Component(modules = [RealmModule::class])
interface RealmComponent {

    fun inject(championshipViewModel: ChampionshipViewModel)
    fun inject(seasonViewModel: SeasonViewModel)
    fun inject(csvParseWorker: CSVParseWorker)
    fun inject(footballDataApplication: FootballDataApplication)
}