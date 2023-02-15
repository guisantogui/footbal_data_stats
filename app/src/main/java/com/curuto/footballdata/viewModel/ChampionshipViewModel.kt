package com.curuto.footballdata.viewModel

import android.content.Context
import android.view.View
import androidx.work.*
import com.curuto.footballdata.DaggerFootbalDataApplicationComponent
import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.utils.DOWNLOAD_FOOTBALLDATA
import com.google.android.material.snackbar.Snackbar
import com.google.common.util.concurrent.ListenableFuture
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmResults
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

    fun donwloadChampionshipData(): View.OnClickListener {
        return View.OnClickListener {
            Snackbar.make(it.rootView, "HELLO TEXT", Snackbar.LENGTH_LONG).show()

            val worker = WorkManager.getInstance(it.context)


            worker.beginUniqueWork(DOWNLOAD_FOOTBALLDATA,
                                    ExistingWorkPolicy.APPEND,
                                    OneTimeWorkRequest.from(Donwlaod::class.java)).enqueue()

        }

        //WorkManager


    }
}

class Donwlaod(appContext: Context, workerParams: WorkerParameters) :
    ListenableWorker(appContext, workerParams) {

    override fun startWork(): ListenableFuture<Result> {
        TODO("Not yet implemented")
    }

}

@Module
class ChampionshipViewModelModule {

    @Provides
    fun getChampionshipViewModel(): ChampionshipViewModel{
        return ChampionshipViewModel()
    }
}