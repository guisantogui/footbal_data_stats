package com.curuto.footballdata

import android.app.Application
import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.model.Match
import com.curuto.footballdata.model.Season
import com.curuto.footballdata.utils.FIRST_RUN
import com.curuto.footballdata.utils.getStringSharedPreferences
import com.curuto.footballdata.utils.logD
import com.curuto.footballdata.utils.updateSharedPreferences
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import org.json.JSONArray
import java.util.*

class FootballDataApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .name("footballdata.db")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(config)


        val isFirstRun = getStringSharedPreferences(this, FIRST_RUN) != "1"
        if (isFirstRun) {
            initDatabase()
            updateSharedPreferences(this, FIRST_RUN, "1")

            logD("DATABASE INITIALIZED")
        }

        logD("Custom Application onCreate() Finished")
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    private fun initDatabase() {

        val jsonSeedData =
            resources.openRawResource(R.raw.seed_data).bufferedReader().use { it.readText() }

        val jsonObjects = JSONArray(jsonSeedData)
        for (i in 0 until jsonObjects.length()) {
            if (!jsonObjects.isNull(i)) {

                val item = jsonObjects.optJSONObject(i)
                val championshipName = item.getString("name")
                val championshipCode = item.getString("championship_code")
                val seasons = item.getJSONArray("seasons")

                val seasonList = RealmList<Season>()

                for (j in 0 until seasons.length()) {

                    val innerItem = seasons.optJSONObject(j)

                    val period = innerItem.getString("season")
                    val downloadDataLink = innerItem.getString("download_data")
                    val seasonCode = innerItem.getString("season_code")

                    seasonList.add(Season(UUID.randomUUID(), downloadDataLink, seasonCode, period, RealmList<Match>()))
                }

                val championship = Championship(UUID.randomUUID(), championshipName, championshipCode, seasonList)

                Realm.getDefaultInstance().executeTransactionAsync {
                    it.insertOrUpdate(championship)
                }

            }
        }
    }
}