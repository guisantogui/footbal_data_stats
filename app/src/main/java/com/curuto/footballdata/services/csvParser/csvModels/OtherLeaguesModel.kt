package com.curuto.footballdata.services.csvParser.csvModels

import com.curuto.footballdata.model.Match
import com.curuto.footballdata.model.Team
import com.curuto.footballdata.repository.TeamRepository
import io.realm.Realm
import org.joda.time.DateTime
import java.util.*

class OtherLeaguesModel: CSVModel() {

    override var columnModelList = listOf("Country", "League", "Season", "Date", "Time", "Home", "Away", "HG", "AG", "Res")


    override fun getMatch(array: Array<String>): Match {
        val country = array.indexOf("Country")
        val league = array.indexOf("League")
        val season = array.indexOf("Season")
        val dateIndex = array.indexOf("Date")
        val timeIndex = array.indexOf("Time")
        val homeTeamIndex = array.indexOf("Home")
        val awayTeamIndex = array.indexOf("Away")
        val hgIndex = array.indexOf("HG")
        val agIndex = array.indexOf("AG")
        val resIndex = array.indexOf("Res")

        val teamRepository = TeamRepository()

        /*var homeTeam = teamRepository.getTeamByName(array[homeTeamIndex])
        if(homeTeam == null){
            homeTeam = Team(UUID.randomUUID(), array[homeTeamIndex])
        }

        var awayTeam = teamRepository.getTeamByName(array[awayTeamIndex])
        if(awayTeam == null){
            awayTeam = Team(UUID.randomUUID(), array[awayTeamIndex])
        }*/

        val date = DateTime.parse(array[dateIndex].trim()+" "+array[timeIndex].trim())
        val res = array[resIndex].trim()

        val hg = Integer.parseInt(array[hgIndex].trim())
        val ag = Integer.parseInt(array[agIndex].trim())

        val match = Match(UUID.randomUUID(), null, null, date.toDate(), res, hg, ag)

        return match
    }

    override fun getHomeTeam(array: Array<String>): String {
        val homeTeamIndex = array.indexOf("Home")

        return array[homeTeamIndex]
    }

    override fun getAwayTeam(array: Array<String>): String {
        val homeTeamIndex = array.indexOf("Away")

        return array[homeTeamIndex]
    }
}