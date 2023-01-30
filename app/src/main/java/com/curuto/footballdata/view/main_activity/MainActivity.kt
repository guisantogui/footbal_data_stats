package com.curuto.footballdata.view.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.curuto.footballdata.FootballDataApplication
import com.curuto.footballdata.databinding.ActivityMainBinding
import com.curuto.footballdata.view.main_activity.adapter.ChampionshipAdapter

class MainActivity : AppCompatActivity() {


    lateinit var championshipAdapter : ChampionshipAdapter
    //@Inject lateinit var myNumber: Championship

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Não quero injetar aplicação
        (applicationContext as FootballDataApplication).myComp.inject(this)




        binding.rvChampionshipList.adapter = championshipAdapter

    }
}