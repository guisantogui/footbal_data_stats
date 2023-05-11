package com.curuto.footballdata.view.championship_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.curuto.footballdata.DaggerFootballDataApplicationComponent
import com.curuto.footballdata.databinding.ActivityChampionshipDetailBinding
import com.curuto.footballdata.repository.realm.DaggerRealmComponent
import com.curuto.footballdata.utils.EXTRA_ID
import com.curuto.footballdata.utils.logD
import com.curuto.footballdata.view.championship_detail.adapter.TeamListAdapter
import com.curuto.footballdata.view.championship_detail.adapter.TeamListModule
import com.curuto.footballdata.view.championship_detail.view_model.ChampionshipDetailViewModel
import java.util.*
import javax.inject.Inject

class ChampionshipDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityChampionshipDetailBinding

    @Inject lateinit var championshipDetailViewModel: ChampionshipDetailViewModel
    @Inject lateinit var teamListAdapter: TeamListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChampionshipDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val championshipId = intent.getStringExtra(EXTRA_ID)

        val component = DaggerChampionshipDetailComponent.builder()
                .teamListModule(TeamListModule(UUID.fromString(championshipId))).build()
        component.inject(this)

        championshipDetailViewModel.setCurrentChampionshipId(championshipId!!)

        binding.rvTeamList.adapter = teamListAdapter
        binding.rvTeamList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


    }

    override fun onResume() {
        super.onResume()

        val allMatches = championshipDetailViewModel.getAllMatches()
        if (allMatches != null && allMatches.isNotEmpty()) {

            val totalMatches = allMatches.size
            binding.tvTotalMatches.text = totalMatches.toString()

            val allGoals = allMatches.sumOf { it.awayTeamGoals + it.homeTeamGoals }
            val avgGoals = allGoals / totalMatches

            binding.tvAverageGoals.text = avgGoals.toString()

        }
    }
}