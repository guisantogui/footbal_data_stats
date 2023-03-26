package com.curuto.footballdata.view.season_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.curuto.footballdata.databinding.ActivityMainBinding
import com.curuto.footballdata.utils.EXTRA_ID
import com.curuto.footballdata.utils.showSnackbar
import com.curuto.footballdata.view.custom.OnRowClicked
import com.curuto.footballdata.view.season_activity.adapter.SeasonAdapter
import com.curuto.footballdata.view.season_activity.adapter.SeasonAdapterModule
import com.curuto.footballdata.view.season_activity.view_model.SeasonViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*
import javax.inject.Inject


class SeasonActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject lateinit var seasonAdapter: SeasonAdapter
    @Inject lateinit var seasonViewModel: SeasonViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val championshipId = UUID.fromString(intent.getStringExtra(EXTRA_ID))

        val component =
            DaggerSeasonComponent.builder().seasonAdapterModule(
                SeasonAdapterModule(
                    downloadChampionshipSeasonData(),
                    championshipId
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
                     seasonViewModel.downloadSeasonData(item, this@SeasonActivity)
                 }

                showSnackbar(binding.llcChampionshipListRoot, "Clicked DOWNLOAD", Snackbar.LENGTH_SHORT)
            }
        }
    }

    private fun openSeasonDataDetails(): OnRowClicked {
        return object : OnRowClicked {
            override fun onPositionClicked(index: Int) {
                //val item = seasonAdapter.getItem(index)

               /* if(item != null){
                    val a = true
                }*/
            }
        }
    }
}