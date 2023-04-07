package com.curuto.footballdata.repository

import com.curuto.footballdata.model.Match
import com.curuto.footballdata.model.Season
import com.curuto.footballdata.repository.realm.DaggerRealmComponent
import io.realm.Realm
import javax.inject.Inject

class SeasonRepository @Inject constructor()  {

    fun insertOrUpdateSeason(realm: Realm, season: Season){
        realm.executeTransaction {
            it.insertOrUpdate(season)
        }
    }

    fun attachMatchesToSeason(realm: Realm, season: Season, matches: List<Match>){
        realm.executeTransaction {
            season.matches.addAll(matches)
            it.insertOrUpdate(season)
        }
    }

}