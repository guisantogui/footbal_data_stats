package com.curuto.footballdata.services.csvParser

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.curuto.footballdata.repository.MatchRepository
import com.curuto.footballdata.repository.TeamRepository
import com.curuto.footballdata.services.EasyDownloadManager
import com.curuto.footballdata.services.csvParser.csvModels.*
import com.curuto.footballdata.utils.DOWNLOAD_ID
import com.curuto.footballdata.utils.logD
import com.curuto.footballdata.utils.logE
import com.opencsv.CSVReader
import java.io.File
import javax.inject.Inject


class CSVParseWorker(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    @Inject lateinit var matchRepository: MatchRepository
    @Inject lateinit var teamRepository: TeamRepository

    override fun doWork(): Result {

        val downloadId = inputData.getLong(DOWNLOAD_ID, -1L)

        val reader = CSVReader(EasyDownloadManager.getFileFromId(context, downloadId))
        val lines = reader.readAll()

        val models =
            listOf(PremierLeague19941995Model(), PremierLeague19962000Model(), PremierLeague20012002Model(),
                PremierLeague20032018Model(), PremierLeague20192023Model(), OtherLeaguesModel()
            )

        val columns = lines[0]

        for (model in models) {
            if (model.columnModelList.size == columns.size && model.matchDownloadedModel(columns)) {
                for (i in 1..lines.size) {
                    val line = lines[i]
                    val match = model.readLine(line)

                    teamRepository.insertTeam(match.homeTeam!!)
                    teamRepository.insertTeam(match.awayTeam!!)

                    matchRepository.insertMatch(match)
                }
            }
        }

        val uri = EasyDownloadManager.getURI(context, downloadId)
        val file = File(uri.path)
        if(file.exists()){
            val deleted = file.delete()

            logD("Deleted? $deleted")
        }
        else{
            logE("File do not exists")
        }



        //TODO: remover o arquivo
        return Result.success()
    }
}