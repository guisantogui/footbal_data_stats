package com.curuto.footballdata.view.season_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.curuto.footballdata.databinding.ActivityMainBinding
import com.curuto.footballdata.utils.showSnackbar
import com.curuto.footballdata.view.custom.OnRowClicked
import com.curuto.footballdata.view.season_activity.adapter.SeasonAdapter
import com.curuto.footballdata.view.season_activity.adapter.SeasonAdapterModule
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class SeasonActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject lateinit var seasonAdapter: SeasonAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val component =
            DaggerSeasonComponent.builder().seasonAdapterModule(
                SeasonAdapterModule(
                    downloadChampionshipSeasonData(),
                    openSeasonDataDetails()
                )
            ).build()

        component.inject(this)

        binding.rvChampionshipList.adapter = seasonAdapter
        binding.rvChampionshipList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun downloadChampionshipSeasonData(): OnRowClicked {
        return object : OnRowClicked {
            override fun onPositionClicked(index: Int) {
                 val item = seasonAdapter.getItem(index)

                 if(item != null){
                     //championshipViewModel.donwloadChampionshipData(item, applicationContext)
                 }

                showSnackbar(binding.llcChampionshipListRoot, "Clicked DOWLOAD", Snackbar.LENGTH_SHORT)
            }
        }
    }

    private fun openSeasonDataDetails(): OnRowClicked {
        return object : OnRowClicked {
            override fun onPositionClicked(index: Int) {
                val item = seasonAdapter.getItem(index)

                if(item != null){
                    //championshipViewModel.donwloadChampionshipData(item, applicationContext)
                }

                showSnackbar(binding.llcChampionshipListRoot, "Clicked ITEM", Snackbar.LENGTH_SHORT)
            }
        }
    }
}