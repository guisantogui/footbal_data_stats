package com.curuto.footballdata.services.csvParser.csvModels

import com.curuto.footballdata.model.Match
import com.curuto.footballdata.model.Team
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatterBuilder
import java.util.*

class PremierLeague20192023Model : CSVModel() {

    override var columnModelList = listOf("Div","Date","Time","HomeTeam","AwayTeam","FTHG","FTAG","FTR","HTHG","HTAG","HTR","Referee","HS","AS","HST","AST","HF","AF","HC","AC","HY","AY","HR","AR","B365H","B365D","B365A","BWH","BWD","BWA","IWH","IWD","IWA","PSH","PSD","PSA","WHH","WHD","WHA","VCH","VCD","VCA","MaxH","MaxD","MaxA","AvgH","AvgD","AvgA","B365>2.5","B365<2.5","P>2.5","P<2.5","Max>2.5","Max<2.5","Avg>2.5","Avg<2.5","AHh","B365AHH","B365AHA","PAHH","PAHA","MaxAHH","MaxAHA","AvgAHH","AvgAHA","B365CH","B365CD","B365CA","BWCH","BWCD","BWCA","IWCH","IWCD","IWCA","PSCH","PSCD","PSCA","WHCH","WHCD","WHCA","VCCH","VCCD","VCCA","MaxCH","MaxCD","MaxCA","AvgCH","AvgCD","AvgCA","B365C>2.5","B365C<2.5","PC>2.5","PC<2.5","MaxC>2.5","MaxC<2.5","AvgC>2.5","AvgC<2.5","AHCh","B365CAHH","B365CAHA","PCAHH","PCAHA","MaxCAHH","MaxCAHA","AvgCAHH","AvgCAHA")

    override fun getMatch(array: Array<String>): Match {
        val divIndex = columnModelList.indexOf("Div")
        val dateIndex = columnModelList.indexOf("Date")
        val timeIndex = columnModelList.indexOf("Time")
        val fthgIndex = columnModelList.indexOf("FTHG")
        val ftagIndex = columnModelList.indexOf("FTAG")
        val ftrIndex = columnModelList.indexOf("FTR")

        val format = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm")

        val date = DateTime.parse(array[dateIndex].trim()+" "+array[timeIndex].trim(), format)
        val ftr = array[ftrIndex].trim()

        val fthg = Integer.parseInt(array[fthgIndex].trim())
        val ftag = Integer.parseInt(array[ftagIndex].trim())

        val match = Match(UUID.randomUUID(), null, null, date.toDate(), ftr, fthg,
                            ftag, 0, 0, 0)

        return match
    }

    override fun getHomeTeam(array: Array<String>): String {
        val homeTeamIndex = columnModelList.indexOf("HomeTeam")

        return array[homeTeamIndex]
    }

    override fun getAwayTeam(array: Array<String>): String {
        val awayTeamIndex = columnModelList.indexOf("AwayTeam")

        return array[awayTeamIndex]
    }
}

class PremierLeague20032018Model : CSVModel() {

    override var columnModelList = listOf("Div", "Date", "HomeTeam", "AwayTeam", "FTHG", "FTAG", "FTR", "HTHG", "HTAG", "HTR", "Referee", "HS", "AS", "HST", "AST", "HF", "AF", "HC", "AC", "HY", "AY", "HR", "AR")

    override fun getMatch(array: Array<String>): Match {
        val divIndex = array.indexOf("Div")
        val dateIndex = array.indexOf("Date")
        val fthgIndex = array.indexOf("FTHG")
        val ftagIndex = array.indexOf("FTAG")
        val ftrIndex = array.indexOf("FTR")

        val date = DateTime.parse(array[dateIndex].trim())
        val ftr = array[ftrIndex].trim()

        val fthg = Integer.parseInt(array[fthgIndex].trim())
        val ftag = Integer.parseInt(array[ftagIndex].trim())

        val match = Match(UUID.randomUUID(), null, null, date.toDate(), ftr, fthg,
                        ftag, 0, 0, 0)

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
        val fthgIndex = array.indexOf("FTHG")
        val ftagIndex = array.indexOf("FTAG")
        val ftrIndex = array.indexOf("FTR")


        val date = DateTime.parse(array[dateIndex].trim())
        val ftr = array[ftrIndex].trim()

        val fthg = Integer.parseInt(array[fthgIndex].trim())
        val ftag = Integer.parseInt(array[ftagIndex].trim())

        val match = Match(UUID.randomUUID(), null, null, date.toDate(), ftr, fthg,
                        ftag, 0, 0, 0)

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
        val fthgIndex = array.indexOf("FTHG")
        val ftagIndex = array.indexOf("FTAG")
        val ftrIndex = array.indexOf("FTR")

        val date = DateTime.parse(array[dateIndex].trim())
        val ftr = array[ftrIndex].trim()

        val fthg = Integer.parseInt(array[fthgIndex].trim())
        val ftag = Integer.parseInt(array[ftagIndex].trim())

        val match = Match(UUID.randomUUID(), null, null, date.toDate(), ftr, fthg,
                        ftag, 0, 0, 0)

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
        val fthgIndex = array.indexOf("FTHG")
        val ftagIndex = array.indexOf("FTAG")
        val ftrIndex = array.indexOf("FTR")

        val date = DateTime.parse(array[dateIndex].trim())
        val ftr = array[ftrIndex].trim()

        val fthg = Integer.parseInt(array[fthgIndex].trim())
        val ftag = Integer.parseInt(array[ftagIndex].trim())

        val match = Match(UUID.randomUUID(), null, null, date.toDate(), ftr, fthg,
                    ftag, 0, 0, 0)

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