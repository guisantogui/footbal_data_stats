package com.curuto.footballdata.services

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.curuto.footballdata.model.Match
import com.curuto.footballdata.model.Team
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


        var homeTeamIndex = -1;
        var awayTeamIndex = -1;
        var dateIndex = -1;
        var timeIndex = -1;

        for( i in 0 .. lines.size){
            if(i == 0){
                //cabeçalhos
                homeTeamIndex = lines[i].indexOf("HomeTeam")
                awayTeamIndex = lines[i].indexOf("AwayTeam")
                dateIndex = lines[i].indexOf("Date")
                timeIndex = lines[i].indexOf("Time")

            }
            else {
                //dados
                val line = lines[i]

                for(j in 0 .. line.size){
                    if(homeTeamIndex >= 0 && homeTeamIndex < line.size){

                    }

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