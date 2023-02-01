package com.curuto.footballdata.view.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.curuto.footballdata.FootballDataApplication
import com.curuto.footballdata.databinding.ActivityMainBinding
import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.utils.logD
import com.curuto.footballdata.view.main_activity.adapter.ChampionshipAdapter
import io.realm.OrderedRealmCollection
import io.realm.Realm
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    lateinit var championshipAdapter : ChampionshipAdapter
    @Inject lateinit var championship: Championship

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        //Não quero injetar aplicação
        (applicationContext as FootballDataApplication).myComp.inject(this)


        val all = Realm.getDefaultInstance().where(Championship::class.java).findAll()

        //TODO: Fazer injeção do adapter do RealmRecyclerView
        logD("Contagem "+all.count())
        championshipAdapter = ChampionshipAdapter(all)

        binding.rvChampionshipList.adapter = championshipAdapter
        binding.rvChampionshipList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}