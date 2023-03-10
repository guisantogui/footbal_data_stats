package com.curuto.footballdata.view.season_activity

import com.curuto.footballdata.view.season_activity.adapter.SeasonAdapterModule
import dagger.Component


@Component(modules = [SeasonAdapterModule::class])
interface SeasonComponent {

    fun inject(seasonActivity: SeasonActivity)

}