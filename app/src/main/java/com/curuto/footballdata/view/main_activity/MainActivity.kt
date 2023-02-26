package com.curuto.footballdata.view.main_activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.curuto.footballdata.DaggerFootbalDataApplicationComponent
import com.curuto.footballdata.FootballDataApplication
import com.curuto.footballdata.databinding.ActivityMainBinding
import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.utils.logD
import com.curuto.footballdata.view.custom.OnRowClicked
import com.curuto.footballdata.view.main_activity.adapter.ChampionshipAdapter
import com.curuto.footballdata.view.main_activity.adapter.ChampionshipAdapterModule
import com.curuto.footballdata.viewModel.ChampionshipViewModel
import io.realm.Realm
import javax.inject.Inject

class MainActivity : AppCompatActivity(), View.OnClickListener {


    @Inject lateinit var championshipAdapter : ChampionshipAdapter
    @Inject lateinit var championshipViewModel: ChampionshipViewModel
    @Inject lateinit var realm: Realm

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Deixando de criar componente na application, e criando na activity
        val myComp = DaggerFootbalDataApplicationComponent.builder()
                        .championshipAdapterModule(ChampionshipAdapterModule(donwloadChampionshipData()))
                        .build()

        myComp.inject(this)

        logD(championshipAdapter.rettext())

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
            }
        }
    }
}