package com.curuto.footballdata.services

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.curuto.footballdata.utils.DOWNLOAD_ID
import com.curuto.footballdata.utils.logD
import com.opencsv.CSVReader
import java.io.FileReader
import java.io.InputStream

class CSVParseWorker(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {


    override fun doWork(): Result {

        val downloadId = inputData.getLong(DOWNLOAD_ID, -1L)

        val query = DownloadManager.Query()
        query.setFilterById(downloadId)

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri: Uri = downloadManager.getUriForDownloadedFile(downloadId)


       // val input = context.contentResolver.uncanonicalize()





       /* logD("CAMINHO: "+uri.path)

        var nextLine: Array<String>

        while (reader.readNext().also { nextLine = it } != null) {


            logD("NEXT LINE: "+nextLine[3])
        }*/



        return Result.success()
    }
}