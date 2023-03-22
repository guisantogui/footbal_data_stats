package com.curuto.footballdata.services.csvParser

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.curuto.footballdata.repository.MatchRepository
import com.curuto.footballdata.repository.TeamRepository
import com.curuto.footballdata.services.EasyDownloadManager
import com.curuto.footballdata.services.csvParser.csvModels.*
import com.curuto.footballdata.utils.DOWNLOAD_ID
import com.curuto.footballdata.utils.FILE_PATH
import com.curuto.footballdata.utils.logD
import com.curuto.footballdata.utils.logE
import com.opencsv.CSVReader
import java.io.File
import java.nio.file.Files
import javax.inject.Inject
import android.provider.MediaStore





class CSVParseWorker(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    @Inject lateinit var matchRepository: MatchRepository
    @Inject lateinit var teamRepository: TeamRepository

    override fun doWork(): Result {

        val downloadId = inputData.getLong(DOWNLOAD_ID, -1L)
        val rawFilePath = inputData.getString(FILE_PATH)

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

        reader.close()


        //TODO: remover o arquivo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val uri: Uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI
            logD("Path $uri.path")
            val selection = MediaStore.Downloads._ID + "=?"
            logD("selection $selection")
            val selectionArgs = arrayOf<String>(java.lang.String.valueOf(downloadId))

            logD("DONWLOAD ID: $downloadId")

            val deleteint = context.contentResolver.delete(uri, selection, selectionArgs)

            logD("status: $deleteint")

        } else {
            val filePath = rawFilePath?.subSequence(7, rawFilePath.length).toString()

            val file = File(filePath)
            if (file.exists()) {
                val deleted = file.delete()

                logD("Deleted? $deleted")
            } else {
                logE("File do not exists")
            }
        }

        val filePath = rawFilePath?.subSequence(7, rawFilePath.length).toString()
        logD(filePath)

        //filePath = "/storage/emulated/0/Download/whatsapp_image_2022-09-22_at_17.12.09.jpeg"

        val file = File(filePath)
        if (file.exists()) {
            val deleted = file.delete()

            logD("Deleted? $deleted")
        } else {
            logE("File do not exists")
        }

        return Result.success()
    }
}