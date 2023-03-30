package com.curuto.footballdata.repository

import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.model.Season
import com.curuto.footballdata.repository.realm.DaggerRealmComponent
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import java.util.*
import javax.inject.Inject

class ChampionshipRepository @Inject constructor() {

    @Inject
    lateinit var realm: Realm

    init {
        DaggerRealmComponent.create().inject(this)
    }

    fun addChampionship(championship: Championship){
        realm.executeTransaction {
            it.insertOrUpdate(championship)
        }
    }

    fun getAllChampionships(): RealmResults<Championship> {
        return realm.where(Championship::class.java).findAllAsync()
    }

    fun getChampionshipCode(championshipId: UUID): String {
        return realm.where(Championship::class.java).equalTo("id", championshipId).findFirst()?.code!!
    }

    fun getAllSeasonsByChampionship(championshipId: UUID): RealmList<Season>? {
        val championship = realm.where(Championship::class.java).equalTo("id", championshipId).findFirst();

        return championship?.seasonList
    }

    fun getSeasonByChampionshipCode(championshipCode: String, championshipSeasonCode: String): Season? {
        val championship = realm.where(Championship::class.java).equalTo("code", championshipCode)
                    .findFirstAsync().seasonList.first { it.code == championshipSeasonCode }

        return championship
    }



}
