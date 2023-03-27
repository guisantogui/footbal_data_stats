package com.curuto.footballdata.view.main_activity.view_model

import android.app.DownloadManager
import android.content.Context
import android.content.IntentFilter
import com.curuto.footballdata.model.Championship
import io.realm.RealmResults
import javax.inject.Inject
import android.os.Environment
import com.curuto.footballdata.model.Season
import com.curuto.footballdata.repository.ChampionshipRepository
import com.curuto.footballdata.repository.realm.DaggerRealmComponent
import com.curuto.footballdata.services.DownloadCompletedBroadcastReceiver
import com.curuto.footballdata.services.EasyDownloadManager
import io.realm.RealmList
import java.util.*


class ChampionshipViewModel @Inject constructor() {

    @Inject lateinit var downloadBroadcastReceiver : DownloadCompletedBroadcastReceiver
    @Inject lateinit var repository: ChampionshipRepository

    init {
        val realmComponent = DaggerRealmComponent.create()
        realmComponent.inject(this)
    }

    fun getAllChampionships(): RealmResults<Championship> {
        return repository.getAllChampionships()
    }

    private fun getAllSeasonsByChampionship(champioshipId: UUID): RealmList<Season>? {
        val seasons = repository.getAllSeasonsByChampionship(champioshipId)

        return seasons
    }

    fun addChampionship(name: String) {
        /*realm.executeTransactionAsync{
            it.insert(Championship(name, "", UUID.randomUUID().toString()))
        }*/
    }


    fun donwloadChampionshipData(championship: Championship, context: Context): Boolean {
        var donwloadQueued = false

        val basePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        if (!basePath.exists()) {
            basePath.mkdirs()
        }

        val seasons = getAllSeasonsByChampionship(championship.id)

        if(seasons != null && seasons.size > 0){
            context.registerReceiver(downloadBroadcastReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
            seasons.forEach {
                EasyDownloadManager.startDowload(context,
                    basePath.absolutePath + "/" + championship.code+"_"+it.code+".csv",
                    it.dataUrl)
            }
            donwloadQueued = true

        }

        return donwloadQueued
    }
}