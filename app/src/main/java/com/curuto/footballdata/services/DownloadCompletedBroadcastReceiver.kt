package com.curuto.footballdata.services

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import androidx.work.*
import com.curuto.footballdata.services.csvParser.CSVParseWorker
import com.curuto.footballdata.utils.*
import java.io.File
import javax.inject.Inject


class DownloadCompletedBroadcastReceiver
                @Inject constructor() : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if(intent.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE){
            val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1)

            if(downloadId != -1L){

                val data = getFileData(context, downloadId)

                val workManager = WorkManager.getInstance(context)
                val params = Data.Builder().putAll(data).build()

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

    fun getFileData(context: Context, id: Long): Data{
        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val q = DownloadManager.Query()
        q.setFilterById(id)
        val c: Cursor = manager.query(q)

        var path = ""
        var title = ""
        if (c.moveToFirst()) {
            path = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))
            title = c.getString(c.getColumnIndex(DownloadManager.COLUMN_TITLE))

        }

        val data = Data.Builder().putString(FILE_PATH, path).putString(FILE_NAME, title).build()

        return data
    }
}