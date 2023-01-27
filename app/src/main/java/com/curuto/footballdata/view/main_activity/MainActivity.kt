package com.curuto.footballdata.view.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.curuto.footballdata.databinding.ActivityMainBinding
import com.curuto.footballdata.view.main_activity.adapter.ChampionshipAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    @Inject var championshipAdapter : ChampionshipAdapter? = null

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvChampionshipList.adapter = championshipAdapter

    }
}