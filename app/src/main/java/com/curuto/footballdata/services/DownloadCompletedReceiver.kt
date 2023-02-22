package com.curuto.footballdata.services

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.curuto.footballdata.utils.logD

class DownloadCompletedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if(intent.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE){

            logD("RECEBEU")
        }
    }
}