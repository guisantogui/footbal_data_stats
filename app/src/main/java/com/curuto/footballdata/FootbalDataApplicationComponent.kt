package com.curuto.footballdata

import com.curuto.footballdata.model.ChampionshipModule
import com.curuto.footballdata.view.main_activity.MainActivity
import com.curuto.footballdata.view.main_activity.adapter.ChampionshipAdapterModule
import dagger.Component

@Component(modules = [ChampionshipModule::class, ChampionshipAdapterModule::class])
interface FootbalDataApplicationComponent {

    fun inject(activity: MainActivity)
}
