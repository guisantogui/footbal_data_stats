package com.curuto.footballdata.repository.realm

import com.curuto.footballdata.FootballDataApplication
import com.curuto.footballdata.repository.ChampionshipRepository
import com.curuto.footballdata.repository.MatchRepository
import com.curuto.footballdata.repository.SeasonRepository
import com.curuto.footballdata.services.csvParser.CSVParseWorker
import com.curuto.footballdata.services.csvParser.csvModels.CSVModel
import com.curuto.footballdata.view.championship_detail.view_model.ChampionshipDetailViewModel
import com.curuto.footballdata.view.main_activity.view_model.ChampionshipViewModel
import com.curuto.footballdata.view.season_activity.view_model.SeasonViewModel
import dagger.Component

@Component(modules = [RealmModule::class])
interface RealmComponent {

    fun inject(seasonViewModel: SeasonViewModel)
    fun inject(csvParseWorker: CSVParseWorker)
    fun inject(footballDataApplication: FootballDataApplication)

    fun inject(championshipViewModel: ChampionshipViewModel)
    fun inject(championshipRepository: ChampionshipRepository)
    fun inject(csvModel: CSVModel)

    fun inject(championshipDetailViewModel: ChampionshipDetailViewModel)
    fun inject(matchRepository: MatchRepository)
    fun inject(seasonRepository: SeasonRepository)

}