package com.curuto.footballdata

import android.app.Application
import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.utils.FIRST_RUN
import com.curuto.footballdata.utils.getStringSharedPreferences
import com.curuto.footballdata.utils.logD
import com.curuto.footballdata.utils.updateSharedPreferences
import io.realm.Realm
import io.realm.RealmConfiguration

class FootballDataApplication : Application() {

    val myComp = DaggerFootbalDataApplicationComponent.create()

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .name("footballdata.db")
            .schemaVersion(1)
            .allowQueriesOnUiThread(false)
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(config)

        val isFirstRun = getStringSharedPreferences(this, FIRST_RUN).contentEquals("1")
        if(isFirstRun) {
            initDatabase()
            updateSharedPreferences(this, FIRST_RUN, "1")
        }

        logD("Custom Application onCreate() Finished")
    }
    override fun onTerminate() {
        super.onTerminate()
    }

    private fun initDatabase(){
        val championshipList = resources.getStringArray(R.array.championship_list)

        Realm.getDefaultInstance().executeTransaction { r -> r
            championshipList.forEach { x -> r.insert(Championship(x)) }
        }
    }
}