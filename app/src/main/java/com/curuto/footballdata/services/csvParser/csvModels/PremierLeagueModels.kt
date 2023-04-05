package com.curuto.footballdata.services.csvParser.csvModels

import com.curuto.footballdata.model.Match
import com.curuto.footballdata.model.Team
import org.joda.time.DateTime
import java.util.*

class PremierLeague20192023Model : CSVModel() {

    override var columnModelList = listOf( "Div", "Date", "Time", "HomeTeam", "AwayTeam", "FTHG", "FTAG", "FTR", "HTHG", "HTAG", "HTR", "Referee", "HS", "AS", "HST", "AST", "HF", "AF", "HC", "AC", "HY", "AY", "HR", "AR")

    override fun getMatch(array: Array<String>): Match {
        val divIndex = array.indexOf("Div")
        val dateIndex = array.indexOf("Date")
        val timeIndex = array.indexOf("Time")
        //val homeTeamIndex = array.indexOf("HomeTeam")
        //val awayTeamIndex = array.indexOf("AwayTeam")
        val fthgIndex = array.indexOf("FTHG")
        val ftagIndex = array.indexOf("FTAG")
        val ftrIndex = array.indexOf("FTR")

        val date = DateTime.parse(array[dateIndex].trim()+" "+array[timeIndex].trim())
        val ftr = array[ftrIndex].trim()

        val fthg = Integer.parseInt(array[fthgIndex].trim())
        val ftag = Integer.parseInt(array[ftagIndex].trim())

        val match = Match(UUID.randomUUID(), null, null, date.toDate(), ftr, fthg, ftag)

        return match
    }

    override fun getHomeTeam(array: Array<String>): String {
        val homeTeamIndex = array.indexOf("HomeTeam")

        return array[homeTeamIndex]
    }

    override fun getAwayTeam(array: Array<String>): String {
        val awayTeamIndex = array.indexOf("AwayTeam")

        return array[awayTeamIndex]
    }
}

class PremierLeague20032018Model : CSVModel() {

    override var columnModelList = listOf("Div", "Date", "HomeTeam", "AwayTeam", "FTHG", "FTAG", "FTR", "HTHG", "HTAG", "HTR", "Referee", "HS", "AS", "HST", "AST", "HF", "AF", "HC", "AC", "HY", "AY", "HR", "AR")

    override fun getMatch(array: Array<String>): Match {
        val divIndex = array.indexOf("Div")
        val dateIndex = array.indexOf("Date")
        val homeTeamIndex = array.indexOf("HomeTeam")
        val awayTeamIndex = array.indexOf("AwayTeam")
        val fthgIndex = array.indexOf("FTHG")
        val ftagIndex = array.indexOf("FTAG")
        val ftrIndex = array.indexOf("FTR")

        //val homeTeam = getTeam(array[homeTeamIndex])
        //val awayTeam = getTeam(array[awayTeamIndex])

        val date = DateTime.parse(array[dateIndex].trim())
        val ftr = array[ftrIndex].trim()

        val fthg = Integer.parseInt(array[fthgIndex].trim())
        val ftag = Integer.parseInt(array[ftagIndex].trim())

        val match = Match(UUID.randomUUID(), null, null, date.toDate(), ftr, fthg, ftag)

        return match
    }

    override fun getHomeTeam(array: Array<String>): String {
        val homeTeamIndex = array.indexOf("HomeTeam")

        return array[homeTeamIndex]
    }

    override fun getAwayTeam(array: Array<String>): String {
        val awayTeamIndex = array.indexOf("AwayTeam")

        return array[awayTeamIndex]
    }

}


class PremierLeague20012002Model : CSVModel() {

    override var columnModelList = listOf("Div", "Date", "HomeTeam", "AwayTeam", "FTHG", "FTAG", "FTR", "HTHG", "HTAG", "HTR", "Attendance", "Referee", "HS", "AS", "HST", "AST", "HHW", "AHW", "HF", "AF", "HC", "AC", "HO", "AO", "HY", "AY", "HR", "AR", "HBP","ABP","GBH","GBD","GBA","IWH","IWD","IWA","LBH","LBD","LBA","SBH","SBD","SBA","SYH","SYD","SYA","WHH","WHD","WHA")

    override fun getMatch(array: Array<String>): Match {

        val dateIndex = array.indexOf("Date")
        val homeTeamIndex = array.indexOf("HomeTeam")
        val awayTeamIndex = array.indexOf("AwayTeam")
        val fthgIndex = array.indexOf("FTHG")
        val ftagIndex = array.indexOf("FTAG")
        val ftrIndex = array.indexOf("FTR")

        //val homeTeam = getTeam(array[homeTeamIndex])
        //val awayTeam = getTeam(array[awayTeamIndex])

        val date = DateTime.parse(array[dateIndex].trim())
        val ftr = array[ftrIndex].trim()

        val fthg = Integer.parseInt(array[fthgIndex].trim())
        val ftag = Integer.parseInt(array[ftagIndex].trim())

        val match = Match(UUID.randomUUID(), null, null, date.toDate(), ftr, fthg, ftag)

        return match
    }

    override fun getHomeTeam(array: Array<String>): String {
        val homeTeamIndex = array.indexOf("HomeTeam")

        return array[homeTeamIndex]
    }

    override fun getAwayTeam(array: Array<String>): String {
        val awayTeamIndex = array.indexOf("AwayTeam")

        return array[awayTeamIndex]
    }
}

class PremierLeague19962000Model : CSVModel() {

    override var columnModelList = listOf("Div","Date","HomeTeam","AwayTeam","FTHG","FTAG","FTR","HTHG","HTAG","HTR")

    override fun getMatch(array: Array<String>): Match {
        val dateIndex = array.indexOf("Date")
        val homeTeamIndex = array.indexOf("HomeTeam")
        val awayTeamIndex = array.indexOf("AwayTeam")
        val fthgIndex = array.indexOf("FTHG")
        val ftagIndex = array.indexOf("FTAG")
        val ftrIndex = array.indexOf("FTR")

        //val homeTeam = getTeam(array[homeTeamIndex])
        //val awayTeam = getTeam(array[awayTeamIndex])

        val date = DateTime.parse(array[dateIndex].trim())
        val ftr = array[ftrIndex].trim()

        val fthg = Integer.parseInt(array[fthgIndex].trim())
        val ftag = Integer.parseInt(array[ftagIndex].trim())

        val match = Match(UUID.randomUUID(), null, null, date.toDate(), ftr, fthg, ftag)

        return match
    }

    override fun getHomeTeam(array: Array<String>): String {
        val homeTeamIndex = array.indexOf("HomeTeam")

        return array[homeTeamIndex]
    }

    override fun getAwayTeam(array: Array<String>): String {
        val awayTeamIndex = array.indexOf("AwayTeam")

        return array[awayTeamIndex]
    }
}

class PremierLeague19941995Model : CSVModel() {

    override var columnModelList = listOf("Div","Date","HomeTeam","AwayTeam","FTHG","FTAG","FTR")

    override fun getMatch(array: Array<String>): Match {
        val dateIndex = array.indexOf("Date")
        val homeTeamIndex = array.indexOf("HomeTeam")
        val awayTeamIndex = array.indexOf("AwayTeam")
        val fthgIndex = array.indexOf("FTHG")
        val ftagIndex = array.indexOf("FTAG")
        val ftrIndex = array.indexOf("FTR")

        //val homeTeam = getTeam(array[homeTeamIndex])
        //val awayTeam = getTeam(array[awayTeamIndex])

        val date = DateTime.parse(array[dateIndex].trim())
        val ftr = array[ftrIndex].trim()

        val fthg = Integer.parseInt(array[fthgIndex].trim())
        val ftag = Integer.parseInt(array[ftagIndex].trim())

        val match = Match(UUID.randomUUID(), null, null, date.toDate(), ftr, fthg, ftag)

        return match
    }

    override fun getHomeTeam(array: Array<String>): String {
        val homeTeamIndex = array.indexOf("HomeTeam")

        return array[homeTeamIndex]
    }

    override fun getAwayTeam(array: Array<String>): String {
        val awayTeamIndex = array.indexOf("AwayTeam")

        return array[awayTeamIndex]
    }
}