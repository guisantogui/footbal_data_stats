package com.curuto.footballdata.services

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import java.io.File
import java.io.InputStreamReader

object EasyDownloadManager {

    fun startDowload(context: Context, downloadPath: String, url: String){

        val storedFile = File(downloadPath)
        val request = DownloadManager.Request(Uri.parse(url))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
        request.setDestinationUri(Uri.fromFile(storedFile))
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

    fun getFileFromId(context: Context, id: Long) : InputStreamReader {
        val uri: Uri = getURI(context, id)
        val inputStream = context.contentResolver.openInputStream(uri)
        val inputStreamReader = InputStreamReader(inputStream)

        return inputStreamReader;
    }

    fun getURI(context:Context , id: Long): Uri {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri: Uri = downloadManager.getUriForDownloadedFile(id)

        return uri
    }


}