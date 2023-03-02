package com.curuto.footballdata.services.csvParser

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.curuto.footballdata.model.Team
import com.curuto.footballdata.services.EasyDownloadManager
import com.curuto.footballdata.services.csvParser.csvModels.*
import com.curuto.footballdata.utils.DOWNLOAD_ID
import com.curuto.footballdata.utils.logD
import com.opencsv.CSVReader
import io.realm.Realm
import java.util.*
import javax.inject.Inject


class CSVParseWorker(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    @Inject lateinit var realm: Realm

    override fun doWork(): Result {

        val downloadId = inputData.getLong(DOWNLOAD_ID, -1L)

        val reader = CSVReader(EasyDownloadManager.getFileFromId(context, downloadId))
        val lines = reader.readAll()


/*        val premierLeague20192023 = listOf("Div", "Date", "Time", "HomeTeam", "AwayTeam", "FTHG", "FTAG", "FTR", "HTHG", "HTAG", "HTR", "Referee", "HS", "AS", "HST", "AST", "HF", "AF", "HC", "AC", "HY", "AY", "HR", "AR")
        val premierLeague20032018 = listOf("Div", "Date", "HomeTeam", "AwayTeam", "FTHG", "FTAG", "FTR", "HTHG", "HTAG", "HTR", "Referee", "HS", "AS", "HST", "AST", "HF", "AF", "HC", "AC", "HY", "AY", "HR", "AR")
        val premierLeague20012002 = listOf("Div", "Date", "HomeTeam", "AwayTeam", "FTHG", "FTAG", "FTR", "HTHG", "HTAG", "HTR", "Attendance", "Referee", "HS", "AS", "HST", "AST", "HHW", "AHW", "HC", "AC", "HF", "AF", "HO", "AO", "HY", "AY", "HR", "AR", "HBP", "ABP")
        val premierLeague19962000 = listOf("Div",  "Date",  "HomeTeam",  "AwayTeam",  "FTHG",  "FTAG",  "FTR",  "HTHG",  "HTAG",  "HT")
        val premierLeague19941995 = listOf("Div",  "Date",  "HomeTeam",  "AwayTeam",  "FTHG",  "FTAG",  "FTR")
        val otherLeaguesModel = listOf("Country", "League", "Season", "Date", "Time", "Home", "Away", "HG", "AG", "Res")

*/

        val models = listOf(/*PremierLeague19941995Model(), PremierLeague19962000Model(), PremierLeague20012002Model(),*/
                            PremierLeague20032018Model(), PremierLeague20192023Model(), OtherLeaguesModel())


        var csvModel: CSVModel
        for( i in 0 .. lines.size){
            if(i == 0){

                for(model in models){
                    if(model.matchDownloadedModel(lines[i])){
                        csvModel = model
                        break
                    }

                }


            }
            else {
                //dados
                val line = lines[i]

                for(j in 0 .. line.size){

                }



                val homeTeamName: String = line[3]
                val awayTeamName: String = line[4]
                val date: String = line[1]
                val time: String = line[2]


                logD("Date: $date Time: $time HomeTeam: $homeTeamName AwayTeam: $awayTeamName")
/*

                var homeTeam = realm.where(Team::class.java).equalTo("name", homeTeamName).findFirst()
                var awayTeam = realm.where(Team::class.java).equalTo("name", awayTeamName).findFirst()

                homeTeam = getTeam(homeTeam, homeTeamName)
                awayTeam = getTeam(awayTeam, awayTeamName)

                val match = Match(UUID.randomUUID(), homeTeam, awayTeam, )
*/
            }


        }

        //TODO: remover o arquivo


        return Result.success()
    }

    private fun getTeam(awayTeam: Team?, homeTeamName: String): Team {

        var awayTeamTemp = awayTeam

        if (awayTeamTemp == null) {
            awayTeamTemp = Team(UUID.randomUUID(), homeTeamName)
            realm.executeTransaction {
                it.insert(awayTeamTemp)
            }
        }
        return awayTeamTemp
    }


}