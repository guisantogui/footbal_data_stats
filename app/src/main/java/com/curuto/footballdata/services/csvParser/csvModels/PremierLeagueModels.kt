package com.curuto.footballdata.services.csvParser.csvModels

import com.curuto.footballdata.model.Match
import com.curuto.footballdata.model.Team
import com.curuto.footballdata.repository.TeamRepository
import org.joda.time.DateTime
import java.util.*

class PremierLeague20192023Model : CSVModel() {

    //TROCAR ESSA LISTA DE COLUNAS POR UM DICIONARIO COM A COLUNA E O SEU INDICE
    override var columnModelList = listOf( "Div", "Date", "Time", "HomeTeam", "AwayTeam", "FTHG", "FTAG", "FTR", "HTHG", "HTAG", "HTR", "Referee", "HS", "AS", "HST", "AST", "HF", "AF", "HC", "AC", "HY", "AY", "HR", "AR")

    override fun readLine(array: Array<String>): Match {
        val divIndex = array.indexOf("Div")
        val dateIndex = array.indexOf("Date")
        val timeIndex = array.indexOf("Time")
        val homeTeamIndex = array.indexOf("HomeTeam")
        val awayTeamIndex = array.indexOf("AwayTeam")
        val fthgIndex = array.indexOf("FTHG")
        val ftagIndex = array.indexOf("FTAG")
        val ftrIndex = array.indexOf("FTR")


        var homeTeam = TeamRepository.getTeamByName(array[homeTeamIndex])
        if(homeTeam == null){
            homeTeam = Team(UUID.randomUUID(), array[homeTeamIndex])
        }

        var awayTeam = TeamRepository.getTeamByName(array[awayTeamIndex])
        if(awayTeam == null){
            awayTeam = Team(UUID.randomUUID(), array[awayTeamIndex])
        }

        val date = DateTime.parse(array[dateIndex].trim()+" "+array[timeIndex].trim())
        val ftr = array[ftrIndex].trim()

        val fthg = Integer.parseInt(array[fthgIndex].trim())
        val ftag = Integer.parseInt(array[ftagIndex].trim())

        val match = Match(UUID.randomUUID(), homeTeam, awayTeam, date.toDate(), ftr, fthg, ftag)

        return match
    }


    /*override fun matchDownloadedModel(downloadedColumns: Array<String>): Boolean {
        val premierLeague20192023 = listOf("Div", "Date", "Time", "HomeTeam", "AwayTeam", "FTHG", "FTAG", "FTR", "HTHG", "HTAG", "HTR", "Referee", "HS", "AS", "HST", "AST", "HF", "AF", "HC", "AC", "HY", "AY", "HR", "AR")

        return premierLeague20192023.all { downloadedColumns.contains(it) }
    }*/
}

class PremierLeague20032018Model : CSVModel() {

    override var columnModelList = listOf("Div", "Date", "HomeTeam", "AwayTeam", "FTHG", "FTAG", "FTR", "HTHG", "HTAG", "HTR", "Referee", "HS", "AS", "HST", "AST", "HF", "AF", "HC", "AC", "HY", "AY", "HR", "AR")

    override fun readLine(array: Array<String>): Match {
        val divIndex = array.indexOf("Div")
        val dateIndex = array.indexOf("Date")
        val homeTeamIndex = array.indexOf("HomeTeam")
        val awayTeamIndex = array.indexOf("AwayTeam")
        val fthgIndex = array.indexOf("FTHG")
        val ftagIndex = array.indexOf("FTAG")
        val ftrIndex = array.indexOf("FTR")


        val homeTeam = Team(UUID.randomUUID(), array[homeTeamIndex])
        val awayTeam = Team(UUID.randomUUID(), array[awayTeamIndex])

        val date = DateTime.parse(array[dateIndex].trim())
        val ftr = array[ftrIndex].trim()

        val fthg = Integer.parseInt(array[fthgIndex].trim())
        val ftag = Integer.parseInt(array[ftagIndex].trim())

        val match = Match(UUID.randomUUID(), homeTeam, awayTeam, date.toDate(), ftr, fthg, ftag)

        return match
    }

/*
    override fun matchDownloadedModel(downloadedColumns: Array<String>): Boolean {
        val premierLeague20032019 = listOf("Div", "Date", "HomeTeam", "AwayTeam", "FTHG", "FTAG", "FTR", "HTHG", "HTAG", "HTR", "Referee", "HS", "AS", "HST", "AST", "HF", "AF", "HC", "AC", "HY", "AY", "HR", "AR")

        return premierLeague20032019.all { downloadedColumns.contains(it) }
    }*/
}
/*
class PremierLeague20012002Model : CSVModel {

    override val id: Int = PREMIERLEAGUE20012002MODEL

    override fun matchDownloadedModel(downloadedColumns: Array<String>): Boolean {
        val premierLeague20012002 = listOf("Div", "Date", "HomeTeam", "AwayTeam", "FTHG", "FTAG", "FTR", "HTHG", "HTAG", "HTR", "Attendance", "Referee", "HS", "AS", "HST", "AST", "HHW", "AHW", "HC", "AC", "HF", "AF", "HO", "AO", "HY", "AY", "HR", "AR", "HBP", "ABP")

        return premierLeague20012002.all { downloadedColumns.contains(it) }
    }
}

class PremierLeague19962000Model : CSVModel {

    override val id: Int = PREMIERLEAGUE19962000MODEL

    override fun matchDownloadedModel(downloadedColumns: Array<String>): Boolean {
        val premierLeague19962000 = listOf("Div",  "Date",  "HomeTeam",  "AwayTeam",  "FTHG",  "FTAG",  "FTR",  "HTHG",  "HTAG",  "HT")

        return premierLeague19962000.all { downloadedColumns.contains(it) }
    }
}

class PremierLeague19941995Model : CSVModel {

    override val id: Int = PREMIERLEAGUE19941995MODEL

    override fun matchDownloadedModel(downloadedColumns: Array<String>): Boolean {
        val premierLeague19941995 = listOf("Div",  "Date",  "HomeTeam",  "AwayTeam",  "FTHG",  "FTAG",  "FTR")

        return premierLeague19941995.all { downloadedColumns.contains(it) }
    }
}*/