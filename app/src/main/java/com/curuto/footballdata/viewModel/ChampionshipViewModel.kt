package com.curuto.footballdata.viewModel

import android.app.DownloadManager
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import com.curuto.footballdata.model.Championship
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import com.curuto.footballdata.model.Season
import com.curuto.footballdata.repository.ChampionshipRepository
import com.curuto.footballdata.repository.realm.DaggerRealmComponent
import com.curuto.footballdata.services.DownloadCompletedBroadcastReceiver
import com.curuto.footballdata.services.EasyDownloadManager
import com.curuto.footballdata.utils.DOWNLOAD
import io.realm.RealmList
import java.io.File
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
        val path = File(Environment.getExternalStorageDirectory(), DOWNLOAD)
        if (!path.exists()) {
            path.mkdirs()
        }

        val seasons = getAllSeasonsByChampionship(championship.id)

        if(seasons != null && seasons.size > 0){

            context.registerReceiver(downloadBroadcastReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

            /*seasons.forEach {
                EasyDownloadManager.startDowload(context,
                    path.absolutePath + "/" + championship.code+it.code+it.period +".csv",
                    it.dataUrl)
            }*/

            donwloadQueued = true

        }

        return donwloadQueued
    }
}