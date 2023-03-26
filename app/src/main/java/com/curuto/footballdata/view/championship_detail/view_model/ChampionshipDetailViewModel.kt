package com.curuto.footballdata.view.championship_detail.view_model

import com.curuto.footballdata.repository.MatchRepository
import com.curuto.footballdata.repository.realm.DaggerRealmComponent
import java.util.*
import javax.inject.Inject


class ChampionshipDetailViewModel @Inject constructor() {

    @Inject lateinit var matchRepository: MatchRepository
    lateinit var currentChampionshipId: UUID

    init {
        DaggerRealmComponent.create().inject(this)
    }

    fun setCurrentChampionshipId(id: String){
        currentChampionshipId = UUID.fromString(id)
    }

    fun getAllMatches(){
        matchRepository.getAllMatchesByChampionship(currentChampionshipId)
    }

}