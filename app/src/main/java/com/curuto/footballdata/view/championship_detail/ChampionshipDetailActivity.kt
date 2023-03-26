package com.curuto.footballdata.view.championship_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.curuto.footballdata.databinding.ActivityChampionshipDetailBinding
import com.curuto.footballdata.view.championship_detail.view_model.ChampionshipDetailViewModel
import javax.inject.Inject

class ChampionshipDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityChampionshipDetailBinding
    @Inject lateinit var championshipDetailViewModel: ChampionshipDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChampionshipDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DaggerChampionshipDetailComponent.create().inject(this)
    }

    override fun onResume() {
        super.onResume()
    }
}