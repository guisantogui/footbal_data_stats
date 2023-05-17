package com.curuto.footballdata.view.championship_detail

import android.graphics.Color
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
import com.github.mikephil.charting.data.PieEntry
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import com.github.mikephil.charting.formatter.PercentFormatter

import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.utils.ColorTemplate


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



        binding.pcHomeWinsDrawsAwayWins.setUsePercentValues(true);
        binding.pcHomeWinsDrawsAwayWins.description.isEnabled = false;
        binding.pcHomeWinsDrawsAwayWins.setExtraOffsets(5f, 10f, 5f, 5f);

        binding.pcHomeWinsDrawsAwayWins.dragDecelerationFrictionCoef = 0.95f;

        binding.pcHomeWinsDrawsAwayWins.isDrawHoleEnabled = true;
        binding.pcHomeWinsDrawsAwayWins.setHoleColor(Color.WHITE);

        binding.pcHomeWinsDrawsAwayWins.setTransparentCircleColor(Color.WHITE);
        binding.pcHomeWinsDrawsAwayWins.setTransparentCircleAlpha(110);

        binding.pcHomeWinsDrawsAwayWins.holeRadius = 58f;
        binding.pcHomeWinsDrawsAwayWins.transparentCircleRadius = 61f;

        binding.pcHomeWinsDrawsAwayWins.setDrawCenterText(true);

        binding.pcHomeWinsDrawsAwayWins.rotationAngle = 0f;

        binding.pcHomeWinsDrawsAwayWins.isRotationEnabled = true;
        binding.pcHomeWinsDrawsAwayWins.isHighlightPerTapEnabled = true;
    }

    override fun onResume() {
        super.onResume()

        val allMatches = championshipDetailViewModel.getAllMatches()
        if (allMatches != null && allMatches.isNotEmpty()) {

            val totalMatches = allMatches.size
            val allGoals = allMatches.sumOf { it.awayTeamGoals + it.homeTeamGoals }
            val avgGoals = allGoals / totalMatches

            binding.tvTotalMatches.text = totalMatches.toString()
            binding.tvAverageGoals.text = avgGoals.toString()
            binding.tvTotalGoals.text = allGoals.toString()
        }

        val a = PieEntry(30f)
        val b = PieEntry(70f)
        var entries = ArrayList<PieEntry>(2)

        entries.add(a)
        entries.add(b)


        val dataSet = PieDataSet(entries, "Vitórias E derrotas")

        dataSet.colors = ColorTemplate.COLORFUL_COLORS.toMutableList()

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)

        binding.pcHomeWinsDrawsAwayWins.data = data

    }
}