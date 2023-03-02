package com.curuto.footballdata.services.csvParser.csvModels

import com.curuto.footballdata.utils.*

class PremierLeague20192023Model : CSVModel() {

    override var id: Int = PREMIERLEAGUE20192023MODEL

    //TROCAR ESSA LISTA DE COLUNAS POR UM DICIONARIO COM A COLUNA E O SEU INDICE
    override var columnModelList = listOf( "Div", "Date", "Time", "HomeTeam", "AwayTeam", "FTHG", "FTAG", "FTR", "HTHG", "HTAG", "HTR", "Referee", "HS", "AS", "HST", "AST", "HF", "AF", "HC", "AC", "HY", "AY", "HR", "AR")

    //IDÉIA ANTIGA
    //CRIAR AQUI CAMPOS COM INDICES DE CADA COLUNA
    val divIndex = 0;
    val dateIndex = 1;
    val timeIndex = 2;


    /*override fun matchDownloadedModel(downloadedColumns: Array<String>): Boolean {
        val premierLeague20192023 = listOf("Div", "Date", "Time", "HomeTeam", "AwayTeam", "FTHG", "FTAG", "FTR", "HTHG", "HTAG", "HTR", "Referee", "HS", "AS", "HST", "AST", "HF", "AF", "HC", "AC", "HY", "AY", "HR", "AR")

        return premierLeague20192023.all { downloadedColumns.contains(it) }
    }*/
}

class PremierLeague20032018Model : CSVModel() {

    override var id: Int = PREMIERLEAGUE20032018MODEL
    override var columnModelList = listOf("Div", "Date", "HomeTeam", "AwayTeam", "FTHG", "FTAG", "FTR", "HTHG", "HTAG", "HTR", "Referee", "HS", "AS", "HST", "AST", "HF", "AF", "HC", "AC", "HY", "AY", "HR", "AR")

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