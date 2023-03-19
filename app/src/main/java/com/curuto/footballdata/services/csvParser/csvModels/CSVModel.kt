package com.curuto.footballdata.services.csvParser.csvModels

import com.curuto.footballdata.model.Match
import com.curuto.footballdata.model.Team
import com.curuto.footballdata.repository.TeamRepository
import com.curuto.footballdata.repository.realm.DaggerRealmComponent
import java.util.*

abstract class CSVModel {

    open var columnModelList = listOf("")
    lateinit var teamRepository: TeamRepository

    init {
        DaggerRealmComponent.create().inject(this)
    }

    fun matchDownloadedModel(downloadedColumns: Array<String>) : Boolean {
        return columnModelList.all { downloadedColumns.contains(it) }
    }

    fun getTeam(teamName: String): Team {

        var team = teamRepository.getTeamByName(teamName)
        if(team == null){
            team = Team(UUID.randomUUID(), teamName)
        }

        return team
    }

    abstract fun readLine(array: Array<String>): Match
}