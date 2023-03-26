package com.curuto.footballdata.view.season_activity

import com.curuto.footballdata.repository.realm.RealmModule
import com.curuto.footballdata.view.season_activity.adapter.SeasonAdapterModule
import com.curuto.footballdata.view.season_activity.view_model.SeasonViewModel
import dagger.Component


@Component(modules = [SeasonAdapterModule::class, RealmModule::class])
interface SeasonComponent {

    fun inject(seasonActivity: SeasonActivity)
    fun inject(seasonViewModel: SeasonViewModel)

}