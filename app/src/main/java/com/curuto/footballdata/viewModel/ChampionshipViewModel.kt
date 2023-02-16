package com.curuto.footballdata.viewModel

import android.content.Context
import android.view.View
import androidx.concurrent.futures.CallbackToFutureAdapter
import androidx.work.*
import com.curuto.footballdata.DaggerFootbalDataApplicationComponent
import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.utils.DOWNLOAD_FOOTBALLDATA
import com.curuto.footballdata.utils.URL_PARAMETER
import com.google.android.material.snackbar.Snackbar
import com.google.common.util.concurrent.ListenableFuture
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmResults
import java.util.concurrent.Executor
import java.util.concurrent.Future
import java.util.concurrent.FutureTask
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ChampionshipViewModel {

    @Inject lateinit var realm: Realm

    init {
        val myComp = DaggerFootbalDataApplicationComponent.create()
        myComp.inject(this)
    }

    fun getAllChampionships(): RealmResults<Championship> {
        return Realm.getDefaultInstance().where(Championship::class.java).findAllAsync()
    }

    fun addChampionship(name: String){
        /*realm.executeTransactionAsync{
            it.insert(Championship(name, "", UUID.randomUUID().toString()))
        }*/
    }

    fun donwloadChampionshipData(championship: Championship?): View.OnClickListener {
        return View.OnClickListener {
            Snackbar.make(it.rootView, "HELLO TEXT", Snackbar.LENGTH_LONG).show()


            val worker = WorkManager.getInstance(it.context)

            val url = Pair<String, String>(URL_PARAMETER, championship!!.dataUrl)

            val req = OneTimeWorkRequestBuilder<Downlaod>().setInputData(workDataOf(url))

            worker.beginUniqueWork(DOWNLOAD_FOOTBALLDATA,
                                    ExistingWorkPolicy.APPEND,
                                    OneTimeWorkRequest.from(Downlaod::class.java)).enqueue()

        }



    }
}

class Downlaod(appContext: Context, workerParams: WorkerParameters) :
    ListenableWorker(appContext, workerParams) {

    override fun startWork(): ListenableFuture<Result> {

        val a = inputData.getString("HHHH")
        val q = CallbackToFutureAdapter.getFuture<Result> {

        }



        return q
    }

}


@Module
class ChampionshipViewModelModule {

    @Provides
    fun getChampionshipViewModel(): ChampionshipViewModel{
        return ChampionshipViewModel()
    }
}