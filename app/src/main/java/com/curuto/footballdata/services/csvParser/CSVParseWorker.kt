package com.curuto.footballdata.services.csvParser

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.curuto.footballdata.model.Match
import com.curuto.footballdata.repository.ChampionshipRepository
import com.curuto.footballdata.repository.MatchRepository
import com.curuto.footballdata.repository.TeamRepository
import com.curuto.footballdata.services.EasyDownloadManager
import com.curuto.footballdata.services.csvParser.csvModels.*
import com.opencsv.CSVReader
import java.io.File
import javax.inject.Inject
import com.curuto.footballdata.repository.SeasonRepository
import com.curuto.footballdata.repository.realm.DaggerRealmComponent
import com.curuto.footballdata.utils.*
import io.realm.Realm


class CSVParseWorker(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    @Inject
    lateinit var matchRepository: MatchRepository
    @Inject
    lateinit var teamRepository: TeamRepository
    @Inject
    lateinit var championshipRepository: ChampionshipRepository
    @Inject
    lateinit var seasonRepository: SeasonRepository

    init {
        DaggerRealmComponent.create().inject(this)
    }

    override fun doWork(): Result {
        val realm: Realm = Realm.getDefaultInstance()

        val downloadId = inputData.getLong(DOWNLOAD_ID, -1L)
        val rawFilePath = inputData.getString(FILE_PATH)
        val fileName = inputData.getString(FILE_NAME)

        val championshipData = fileName!!.split("_")
        val championshipCode = championshipData[0]
        val championshipSeasonCode = championshipData[1].substring(0, 4)

        val reader = CSVReader(EasyDownloadManager.getFileFromId(context, downloadId))
        val lines = reader.readAll()

        val models =
            listOf(
                PremierLeague19941995Model(),
                PremierLeague19962000Model(),
                PremierLeague20012002Model(),
                PremierLeague20032018Model(),
                PremierLeague20192023Model(),
                OtherLeaguesModel()
            )

        val season = championshipRepository.getSeasonByChampionshipCode(
            realm, championshipCode,
            championshipSeasonCode
        )


        val columns = lines[0]
        val matchList = ArrayList<Match>()

        for (model in models) {
            if (model.columnModelList.size == columns.size && model.matchDownloadedModel(columns)) {
                for (i in 1 until lines.size) {
                    val line = lines[i]
                    val match = model.getMatch(line)
                    val homeTeamName = model.getHomeTeam(line).trim()
                    val awayTeamName = model.getAwayTeam(line).trim()

                    var homeTeam = teamRepository.getTeamByName(realm, homeTeamName)
                    if(homeTeam == null){
                        homeTeam = teamRepository.insertTeam(realm, homeTeamName)
                    }

                    var awayTeam = teamRepository.getTeamByName(realm, homeTeamName)
                    if(awayTeam == null){
                        awayTeam = teamRepository.insertTeam(realm, awayTeamName)
                    }

                    match.homeTeam = homeTeam
                    match.awayTeam = awayTeam

                    val lastMatchHomeTeam = matchList.findLast { it.homeTeam?.name == homeTeamName }
                    val lastMatchAwayTeam = matchList.findLast { it.awayTeam?.name == awayTeamName }

                    if(lastMatchHomeTeam != null && lastMatchAwayTeam != null){
                        when(match.result){
                            "H" -> {
                                lastMatchHomeTeam.homeTeamPointsAmount += 3
                            }
                            "A" -> {
                                lastMatchAwayTeam.awayTeamPointsAmount += 3
                            }
                            "D" -> {
                                lastMatchHomeTeam.homeTeamPointsAmount += 1
                                lastMatchAwayTeam.awayTeamPointsAmount += 1
                            }
                        }
                    }


                    matchRepository.insertMatch(realm, match)
                    matchList.add(match)

                }
            }
        }

        if (season != null) {
            seasonRepository.attachMatchesToSeason(realm, season, matchList)
        }

        reader.close()
        realm.close()

        val filePath = rawFilePath?.subSequence(7, rawFilePath.length).toString()
        val file = File(filePath)

        logD("Absolute path delete file " + file.absolutePath)

        if (file.exists()) {
            val deleted = file.delete()

            logD("Deleted? $deleted")
        } else {
            logE("File do not exists")
        }



        return Result.success()
    }


}