package com.curuto.footballdata.services

import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
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
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri: Uri = downloadManager.getUriForDownloadedFile(id)
        val inputStream = context.contentResolver.openInputStream(uri)
        val inputStreamReader = InputStreamReader(inputStream)

        return inputStreamReader
    }
/**
    fun getURI(context:Context , id: Long): String {

        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val q = DownloadManager.Query()
        q.setFilterById(id)
        val c: Cursor = manager.query(q)

        var title = ""

        if (c.moveToFirst()) {
            title = c.getString(c.getColumnIndex(DownloadManager.COLUMN_TITLE))
            // get other required data by changing the constant passed to getColumnIndex
        }

        return title;

    }*/


}