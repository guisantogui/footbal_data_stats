package com.curuto.footballdata.viewModel

import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.model.Season
import io.realm.Realm
import io.realm.RealmResults
import java.util.*
import javax.inject.Inject


class SeasonViewModel @Inject constructor() {

    @Inject lateinit var realm: Realm

    fun getAllSeasonsByChampionship(championshipId: UUID): RealmResults<Season> {
        return realm.where(Season::class.java).findAllAsync()
    }

}