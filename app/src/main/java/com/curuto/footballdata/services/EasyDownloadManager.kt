package com.curuto.footballdata.services

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import com.curuto.footballdata.utils.logD
import java.io.File

object EasyDownloadManager {

    fun startDowload(context: Context, downloadPath: String, url: String){

        logD(downloadPath)
        val storedCSV = File(downloadPath)
        val request = DownloadManager.Request(Uri.parse(url))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
        request.setDestinationUri(Uri.fromFile(storedCSV))
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        val donwloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        donwloadManager.enqueue(request)
    }
}