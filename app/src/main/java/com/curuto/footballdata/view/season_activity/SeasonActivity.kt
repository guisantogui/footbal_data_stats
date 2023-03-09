package com.curuto.footballdata.view.season_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.curuto.footballdata.databinding.ActivityMainBinding
import com.curuto.footballdata.utils.showSnackbar
import com.curuto.footballdata.view.custom.OnRowClicked
import com.google.android.material.snackbar.Snackbar


class SeasonActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }



    fun donwloadChampionshipSeasonData(): OnRowClicked {
        return object : OnRowClicked {
            override fun onPositionClicked(index: Int) {
               // val item = championshipAdapter.getItem(index)

               /* if(item != null){
                    championshipViewModel.donwloadChampionshipData(item, applicationContext)
                }*/

                showSnackbar(binding.llcChampionshipListRoot, "Clicked", Snackbar.LENGTH_SHORT)
            }
        }
    }
}