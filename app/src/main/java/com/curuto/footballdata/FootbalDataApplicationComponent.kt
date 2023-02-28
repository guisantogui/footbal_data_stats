package com.curuto.footballdata

import com.curuto.footballdata.repository.realm.RealmModule
import com.curuto.footballdata.view.main_activity.MainActivity
import com.curuto.footballdata.view.main_activity.adapter.ChampionshipAdapterModule
import com.curuto.footballdata.viewModel.ChampionshipViewModel
import dagger.Component

@Component(modules =[
                        ChampionshipAdapterModule::class,
                        RealmModule::class,
                    ])
interface FootbalDataApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(championshipViewModel: ChampionshipViewModel)
}
