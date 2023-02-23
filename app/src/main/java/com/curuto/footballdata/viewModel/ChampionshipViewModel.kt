package com.curuto.footballdata.viewModel

import android.app.DownloadManager
import android.content.Context
import android.content.IntentFilter
import com.curuto.footballdata.DaggerRealmComponent
import com.curuto.footballdata.model.Championship
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject
import android.os.Environment
import com.curuto.footballdata.services.DownloadCompletedReceiver
import com.curuto.footballdata.services.EasyDownloadManager
import java.io.File


class ChampionshipViewModel @Inject constructor() {

    @Inject
    lateinit var realm: Realm

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
        val path = File(Environment.getExternalStorageDirectory(), "Download")
        if (!path.exists()) {
            path.mkdirs()
        }

        EasyDownloadManager.startDowload(context,
                    path.absolutePath + "/" + "E02021-2022.csv",
                            "https://www.football-data.co.uk/mmz4281/2122/E0.csv")

        val receiver = DownloadCompletedReceiver()
        context.registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }
}