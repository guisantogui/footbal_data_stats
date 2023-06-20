package com.curuto.footballdata.view.championship_detail.view_model

import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.model.Match
import com.curuto.footballdata.model.Team
import com.curuto.footballdata.repository.ChampionshipRepository
import com.curuto.footballdata.repository.MatchRepository
import com.curuto.footballdata.repository.TeamRepository
import com.curuto.footballdata.repository.realm.DaggerRealmComponent
import io.realm.Realm
import io.realm.RealmResults
import java.util.*
import javax.inject.Inject


class ChampionshipDetailViewModel @Inject constructor() {

    @Inject lateinit var matchRepository: MatchRepository
    @Inject lateinit var teamRepository: TeamRepository
    @Inject lateinit var championshipRepository: ChampionshipRepository
    @Inject lateinit var realm: Realm
    lateinit var currentChampionshipId: UUID


    init {
        DaggerRealmComponent.create().inject(this)
    }

    fun setCurrentChampionshipId(id: String){
        currentChampionshipId = UUID.fromString(id)
    }

    fun getChampionship(): Championship? {
        return championshipRepository.getChampionshipById(realm, currentChampionshipId)
    }

    fun getAllMatches() : List<Match>? {
        return matchRepository.getAllMatchesByChampionship(realm, currentChampionshipId)
    }

    fun getAllTeams(championshipId: UUID): RealmResults<Team> {
        return teamRepository.getAllTeamsByChampionship(realm, championshipId)
    }

}