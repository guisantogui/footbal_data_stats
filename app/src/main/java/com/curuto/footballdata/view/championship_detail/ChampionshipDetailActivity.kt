package com.curuto.footballdata.view.championship_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.curuto.footballdata.databinding.ActivityChampionshipDetailBinding
import com.curuto.footballdata.repository.realm.DaggerRealmComponent
import com.curuto.footballdata.utils.EXTRA_ID
import com.curuto.footballdata.view.championship_detail.view_model.ChampionshipDetailViewModel
import javax.inject.Inject

class ChampionshipDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityChampionshipDetailBinding

    @Inject
    lateinit var championshipDetailViewModel: ChampionshipDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChampionshipDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DaggerChampionshipDetailComponent.create().inject(this)

        val championshipId = intent.getStringExtra(EXTRA_ID)
        championshipDetailViewModel.setCurrentChampionshipId(championshipId!!)
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