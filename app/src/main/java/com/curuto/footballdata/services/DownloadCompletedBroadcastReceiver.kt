package com.curuto.footballdata.services

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import androidx.work.*
import com.curuto.footballdata.services.csvParser.CSVParseWorker
import com.curuto.footballdata.utils.DOWNLOAD_ID
import com.curuto.footballdata.utils.FILE_PATH
import com.curuto.footballdata.utils.logD
import com.curuto.footballdata.utils.logE
import javax.inject.Inject


class DownloadCompletedBroadcastReceiver
                @Inject constructor() : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if(intent.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE){
            val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1)

            if(downloadId != -1L){

                val path = getFileLocation(context, downloadId)
                logD("LOAD TESTE NOME ARQUIVO: $path")

                val workManager = WorkManager.getInstance(context)
                val params = Data.Builder().putLong(DOWNLOAD_ID, downloadId).putString(FILE_PATH, path).build()

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

    fun getFileLocation(context: Context, id: Long): String{
        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val q = DownloadManager.Query()
        q.setFilterById(id)
        val c: Cursor = manager.query(q)

        var path = ""
        if (c.moveToFirst()) {
            path = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))
        }

        return path;
    }
}