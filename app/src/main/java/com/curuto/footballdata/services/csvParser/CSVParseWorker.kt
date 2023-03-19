package com.curuto.footballdata.services.csvParser

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.curuto.footballdata.repository.MatchRepository
import com.curuto.footballdata.repository.TeamRepository
import com.curuto.footballdata.services.EasyDownloadManager
import com.curuto.footballdata.services.csvParser.csvModels.*
import com.curuto.footballdata.utils.DOWNLOAD_ID
import com.opencsv.CSVReader
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
            if (model.matchDownloadedModel(columns)) {
                for (i in 1..lines.size) {
                    val line = lines[i]
                    val match = model.readLine(line)

                    teamRepository.insertTeam(match.homeTeam!!)
                    teamRepository.insertTeam(match.awayTeam!!)

                    matchRepository.insertMatch(match)
                }
            }
        }
        


        //TODO: remover o arquivo
        return Result.success()
    }
}