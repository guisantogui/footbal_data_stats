package com.curuto.footballdata.viewModel

import android.content.Context
import androidx.concurrent.futures.CallbackToFutureAdapter
import androidx.work.*
import com.curuto.footballdata.DaggerRealmComponent
import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.utils.DOWNLOAD_FOOTBALLDATA
import com.curuto.footballdata.utils.URL_PARAMETER
import com.curuto.footballdata.utils.logD
import com.google.common.util.concurrent.ListenableFuture
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject

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

    fun rettext(): String {
        return "Champs View Model"
    }

    fun donwloadChampionshipData(championship: Championship, context: Context) {

        logD("CAMPEONATO " + championship.name)

        val worker = WorkManager.getInstance(context)
        val url = Pair<String, String>(URL_PARAMETER, championship.name)

        val req = OneTimeWorkRequestBuilder<Download>().setInputData(workDataOf(url))



        worker.beginUniqueWork(DOWNLOAD_FOOTBALLDATA,
                                ExistingWorkPolicy.APPEND,
                                    req.build()).enqueue()
    }
}

class Download(appContext: Context, workerParams: WorkerParameters) :
    ListenableWorker(appContext, workerParams) {

    override fun startWork(): ListenableFuture<Result> {

        val a = inputData.getString(URL_PARAMETER)
        val q = CallbackToFutureAdapter.getFuture<Result> {
            logD("Logando no futuro" + a)
        }

        return q
    }

}