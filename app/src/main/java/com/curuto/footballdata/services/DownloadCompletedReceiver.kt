package com.curuto.footballdata.services

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.*
import com.curuto.footballdata.utils.DOWNLOAD_ID
import com.curuto.footballdata.utils.logE

class DownloadCompletedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if(intent.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE){
            val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1)

            if(downloadId != -1L){

                val workManager = WorkManager.getInstance(context)
                val params = Data.Builder().putLong(DOWNLOAD_ID, downloadId).build()

                val csvParserWorker = OneTimeWorkRequestBuilder<CSVParseWorker>()
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