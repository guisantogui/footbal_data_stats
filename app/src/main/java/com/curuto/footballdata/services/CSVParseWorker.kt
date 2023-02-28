package com.curuto.footballdata.services

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
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


        var nextLine: Array<String>
        while (reader.iterator().hasNext()) {
            nextLine = reader.iterator().next()

            //logD("NEXT LINE: "+nextLine[3])

            val homeTeamName: String = nextLine[3]
            val awayTeamName: String = nextLine[4]

            var homeTeam = realm.where(Team::class.java).equalTo("name", homeTeamName).findFirst()
            var awayTeam = realm.where(Team::class.java).equalTo("name", awayTeamName).findFirst()

            if(homeTeam == null){
                homeTeam = Team(UUID.randomUUID(), homeTeamName)
                realm.executeTransaction {
                    it.insert(homeTeam)
                }
            }

            if(awayTeam == null){
                awayTeam = Team(UUID.randomUUID(), homeTeamName)
                realm.executeTransaction {
                    it.insert(awayTeam)
                }
            }



        }

        //TODO: remover o arquivo


        return Result.success()
    }
}