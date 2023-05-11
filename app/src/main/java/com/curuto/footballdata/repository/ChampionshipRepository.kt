package com.curuto.footballdata.repository

import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.model.Season
import com.curuto.footballdata.model.Team
import com.curuto.footballdata.repository.realm.DaggerRealmComponent
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import java.util.*
import javax.inject.Inject

class ChampionshipRepository @Inject constructor() {


    fun addChampionship(realm: Realm, championship: Championship){
        realm.executeTransaction {
            it.insertOrUpdate(championship)
        }
    }

    fun getAllChampionships(realm: Realm): RealmResults<Championship> {
        return realm.where(Championship::class.java).findAllAsync()
    }

    fun getChampionshipCode(realm: Realm, championshipId: UUID): String {
        return realm.where(Championship::class.java).equalTo("id", championshipId).findFirst()?.code!!
    }

    fun getAllSeasonsByChampionship(realm: Realm, championshipId: UUID): RealmList<Season>? {
        val championship = realm.where(Championship::class.java).equalTo("id", championshipId).findFirst();

        return championship?.seasonList
    }

    fun getSeasonByChampionshipCode(realm: Realm, championshipCode: String, championshipSeasonCode: String): Season? {
        val championship = realm.where(Championship::class.java).equalTo("code", championshipCode)
                    .findFirst()?.seasonList?.first { it.code == championshipSeasonCode }

        return championship
    }

    fun getTeamsByChampionshipCode(realm: Realm, championshipCode: String): List<Team>{
        val champ = realm.where(Championship::class.java)
                    .equalTo("code", championshipCode).findFirst()

        val teams = champ?.seasonList?.flatMap { it.matches }?.map { it.homeTeam!! }!!

        return teams


    }

}
