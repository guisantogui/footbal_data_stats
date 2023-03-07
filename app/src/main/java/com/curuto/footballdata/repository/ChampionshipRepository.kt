package com.curuto.footballdata.repository

import com.curuto.footballdata.model.Championship
import io.realm.Realm
import javax.inject.Inject

class ChampionshipRepository {

    @Inject
    lateinit var realm: Realm

    fun addChampionship(championship: Championship){
        realm.executeTransaction {
            it.insertOrUpdate(championship)
        }
    }
}
