package com.curuto.footballdata.repository

import com.curuto.footballdata.model.Season
import com.curuto.footballdata.repository.realm.DaggerRealmComponent
import io.realm.Realm
import javax.inject.Inject

class SeasonRepository @Inject constructor()  {

    @Inject
    lateinit var realm: Realm

    init {
        DaggerRealmComponent.create().inject(this)
    }

    fun insertOrUpdateSeason(season: Season){
        realm.executeTransaction {
            it.insertOrUpdate(season)
        }
    }
}