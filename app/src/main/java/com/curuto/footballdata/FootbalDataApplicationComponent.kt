package com.curuto.footballdata

import com.curuto.footballdata.model.ChampionshipModule
import com.curuto.footballdata.repository.RealmModule
import com.curuto.footballdata.view.main_activity.MainActivity
import com.curuto.footballdata.view.main_activity.adapter.ChampionshipAdapterModule
import com.curuto.footballdata.viewModel.ChampionshipViewModel
import com.curuto.footballdata.viewModel.ChampionshipViewModelModule
import dagger.Component

@Component(modules =[
                        ChampionshipModule::class,
                        ChampionshipAdapterModule::class,
                        RealmModule::class,
                        ChampionshipViewModelModule::class
                    ])
interface FootbalDataApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(championshipViewModel: ChampionshipViewModel)
}
