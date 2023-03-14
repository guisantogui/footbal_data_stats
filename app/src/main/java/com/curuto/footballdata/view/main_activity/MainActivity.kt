package com.curuto.footballdata.view.main_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.curuto.footballdata.DaggerFootballDataApplicationComponent
import com.curuto.footballdata.databinding.ActivityMainBinding
import com.curuto.footballdata.utils.EXTRA_ID
import com.curuto.footballdata.utils.logD
import com.curuto.footballdata.utils.showSnackbar
import com.curuto.footballdata.view.custom.OnRowClicked
import com.curuto.footballdata.view.main_activity.adapter.ChampionshipAdapter
import com.curuto.footballdata.view.main_activity.adapter.ChampionshipAdapterModule
import com.curuto.footballdata.view.season_activity.SeasonActivity
import com.curuto.footballdata.viewModel.ChampionshipViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainActivity : AppCompatActivity(), View.OnClickListener {


    @Inject lateinit var championshipAdapter : ChampionshipAdapter
    @Inject lateinit var championshipViewModel: ChampionshipViewModel

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Deixando de criar componente na application, e criando na activity
        val myComp = DaggerFootballDataApplicationComponent.builder()
                        .championshipAdapterModule(ChampionshipAdapterModule(donwloadChampionshipData(), onRowItemClicked()))
                        .build()

        myComp.inject(this)

        binding.rvChampionshipList.adapter = championshipAdapter
        binding.rvChampionshipList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        //binding.acbAddChampionship.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            /*R.id.acb_add_championship -> {
                championshipViewModel.addChampionship("SUPER LIGA VIEW MODEL")
                logD("CLICKED ADD CHAMPS")
            }*/
        }
    }


    fun donwloadChampionshipData(): OnRowClicked {
        return object : OnRowClicked {
            override fun onPositionClicked(index: Int) {
                val item = championshipAdapter.getItem(index)

                if(item != null){
                    championshipViewModel.donwloadChampionshipData(item, applicationContext)
                }

                showSnackbar(binding.llcChampionshipListRoot, "Clicked", Snackbar.LENGTH_SHORT)
            }
        }
    }

    fun onRowItemClicked(): OnRowClicked {
        return object : OnRowClicked {
            override fun onPositionClicked(index: Int) {
                val item = championshipAdapter.getItem(index)

                val intent = Intent(this@MainActivity, SeasonActivity::class.java)
                intent.putExtra(EXTRA_ID, item?.id.toString())

                startActivity(intent)

                //Chamar activity Season

                logD("ON ROW CLICKED <<<")
            }
        }
    }
}