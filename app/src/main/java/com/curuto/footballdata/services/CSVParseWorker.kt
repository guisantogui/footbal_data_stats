package com.curuto.footballdata.services

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.curuto.footballdata.utils.DOWNLOAD_ID
import com.curuto.footballdata.utils.logD

class CSVParseWorker(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {


    override fun doWork(): Result {

        val downloadId = inputData.getLong(DOWNLOAD_ID, -1L)

        val query = DownloadManager.Query()
        query.setFilterById(downloadId)

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri: Uri = downloadManager.getUriForDownloadedFile(downloadId)


        logD("CAMINHO: "+uri.path)


        return Result.success()
    }
}