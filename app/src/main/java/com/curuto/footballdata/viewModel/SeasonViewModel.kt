package com.curuto.footballdata.viewModel

import android.app.DownloadManager
import android.content.Context
import android.content.IntentFilter
import android.os.Environment
import com.curuto.footballdata.model.Season
import com.curuto.footballdata.repository.ChampionshipRepository
import com.curuto.footballdata.repository.realm.DaggerRealmComponent
import com.curuto.footballdata.services.DownloadCompletedBroadcastReceiver
import com.curuto.footballdata.services.EasyDownloadManager
import com.curuto.footballdata.utils.DOWNLOAD
import io.realm.RealmList
import java.io.File
import java.util.*
import javax.inject.Inject


class SeasonViewModel @Inject constructor() {

    @Inject lateinit var downloadBroadcastReceiver : DownloadCompletedBroadcastReceiver
    @Inject lateinit var championshipRepository: ChampionshipRepository

    init {
        DaggerRealmComponent.create().inject(this)
    }

    fun getAllSeasonsByChampionship(championshipId: UUID): RealmList<Season>? {
        val seasons = championshipRepository.getAllSeasonsByChampionship(championshipId)

        return seasons
    }

    fun downloadSeasonData(season: Season, context: Context) {

        var path = ""
       /* path = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Downloads.DOWNLOAD_URI + "/" + season.code+season.period+".csv"
        } else {

            val legacyPath = File(Environment.getExternalStorageDirectory(), DOWNLOAD)
            if (!legacyPath.exists()) {
                legacyPath.mkdirs()
            }

            legacyPath.absolutePath + "/" + season.code+season.period+".csv"
        }*/

        val basePath = File(Environment.getExternalStorageDirectory(), DOWNLOAD)
        if (!basePath.exists()) {
            basePath.mkdirs()
        }

        path = basePath.absolutePath + "/" + season.code+season.period+".csv"

        EasyDownloadManager.startDowload(context, path, season.dataUrl)
        context.registerReceiver(downloadBroadcastReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }
}