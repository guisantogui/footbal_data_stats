package com.curuto.footballdata

import android.app.Application
import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.utils.FIRST_RUN
import com.curuto.footballdata.utils.getStringSharedPreferences
import com.curuto.footballdata.utils.logD
import com.curuto.footballdata.utils.updateSharedPreferences
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class FootballDataApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .name("footballdata.db")
            .schemaVersion(1)
            //.allowQueriesOnUiThread(true)
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(config)

        val isFirstRun = getStringSharedPreferences(this, FIRST_RUN) != "1"
        if(isFirstRun) {
            initDatabase()
            updateSharedPreferences(this, FIRST_RUN, "1")

            logD("DATABASE INITIALIZED")
        }

        logD("Custom Application onCreate() Finished")
    }
    override fun onTerminate() {
        super.onTerminate()
    }

    private fun initDatabase(){

        val jsonSeedData = resources.openRawResource(R.raw.seed_data).bufferedReader().use { it.readText() }

        val jsonObjects = JSONArray(jsonSeedData)
        for (i in 0 .. jsonObjects.length()){
            if(!jsonObjects.isNull(i)){
                Realm.getDefaultInstance().executeTransactionAsync { r -> r
                    val item = jsonObjects.optJSONObject(i)


                    /*
                    val championship = Championship(
                                                    UUID.randomUUID(),
                                                    item.getString("name"),
                                                    item.getString("download_data"),
                                                    item.getString("league_code"),
                                                    RealmList())
                    r.insert(championship)
                    */

                }
            }

        }

/*
        val championshipList = resources.getStringArray(R.array.championship_list)

        logD("Time 0 " + championshipList[0])

        Realm.getDefaultInstance().executeTransactionAsync { r -> r
            championshipList.forEach {
                    x -> r.insert(Championship(x, "", UUID.randomUUID().toString())) }
            }

*/
    }
}