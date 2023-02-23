package com.curuto.footballdata.services

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.work.*
import com.curuto.footballdata.utils.DOWNLOAD_ID
import com.curuto.footballdata.utils.logD
import com.curuto.footballdata.utils.logE

class DownloadCompletedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if(intent.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE){
            val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1)

            if(downloadId != -1L){

                val workManager = WorkManager.getInstance(context)
                val params = Data.Builder().putLong(DOWNLOAD_ID, downloadId).build()

                val csvParserWorker = OneTimeWorkRequestBuilder<CSVParser>()
                    .setInputData(params)
                    .build()

                workManager.enqueue(csvParserWorker)
            }
            else{
                logE("Download Id not found")
            }
        }

    }
}

class CSVParser(private val context: Context, workerParameters: WorkerParameters) :
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