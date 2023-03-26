package com.curuto.footballdata.view.championship_detail

import com.curuto.footballdata.view.championship_detail.view_model.ChampionshipDetailViewModel
import dagger.Component

@Component
interface ChampionshipDetailComponent {

    fun inject(championshipDetailActivity: ChampionshipDetailActivity)
}