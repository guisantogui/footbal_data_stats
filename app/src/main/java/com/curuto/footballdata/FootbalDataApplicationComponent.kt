package com.curuto.footballdata

import com.curuto.footballdata.model.ChampionshipModule
import com.curuto.footballdata.view.main_activity.MainActivity
import dagger.Component

@Component(modules = [ChampionshipModule::class])
interface FootbalDataApplicationComponent {

    fun inject(activity: MainActivity)
}
