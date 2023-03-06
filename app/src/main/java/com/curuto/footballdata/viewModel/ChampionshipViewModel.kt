package com.curuto.footballdata.viewModel

import android.app.DownloadManager
import android.content.Context
import android.content.IntentFilter
import com.curuto.footballdata.model.Championship
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject
import android.os.Environment
import com.curuto.footballdata.repository.realm.DaggerRealmComponent
import com.curuto.footballdata.services.DownloadCompletedBroadcastReceiver
import com.curuto.footballdata.services.EasyDownloadManager
import com.curuto.footballdata.utils.DOWNLOAD
import java.io.File


class ChampionshipViewModel @Inject constructor() {

    @Inject lateinit var downloadBroadcastReceiver : DownloadCompletedBroadcastReceiver

    init {
        val realmComponent = DaggerRealmComponent.create()
        realmComponent.inject(this)
    }

    fun getAllChampionships(): RealmResults<Championship> {
        return Realm.getDefaultInstance().where(Championship::class.java).findAllAsync()
    }

    fun addChampionship(name: String) {
        /*realm.executeTransactionAsync{
            it.insert(Championship(name, "", UUID.randomUUID().toString()))
        }*/
    }


    fun donwloadChampionshipData(championship: Championship, context: Context) {
        val path = File(Environment.getExternalStorageDirectory(), DOWNLOAD)
        if (!path.exists()) {
            path.mkdirs()
        }

        EasyDownloadManager.startDowload(context,
                    path.absolutePath + "/" + championship.code+championship.season +".csv",
            "championship.dataUrl")

        context.registerReceiver(downloadBroadcastReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }
}