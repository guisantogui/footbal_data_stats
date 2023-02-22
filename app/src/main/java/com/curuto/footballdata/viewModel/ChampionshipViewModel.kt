package com.curuto.footballdata.viewModel

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import androidx.work.*
import com.curuto.footballdata.DaggerRealmComponent
import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.utils.logD
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject
import android.os.Environment
import com.curuto.footballdata.services.EasyDownloadManager
import com.curuto.footballdata.utils.NAME_PARAMETER
import com.curuto.footballdata.utils.URL_PARAMETER
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

    }
}

class CSVParser(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    /*
        worker = WorkManager.getInstance(context)
        val url = Pair(NAME_PARAMETER, championship.name)

        val req = OneTimeWorkRequestBuilder<CSVParser>().setInputData(workDataOf(url))
*/
    override fun doWork(): Result {

        val championshipName = inputData.getString(NAME_PARAMETER)
        val championshipUrl = inputData.getString(URL_PARAMETER)


        return Result.success()
    }

}