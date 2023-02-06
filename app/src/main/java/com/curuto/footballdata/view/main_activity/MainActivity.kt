package com.curuto.footballdata.view.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.curuto.footballdata.FootballDataApplication
import com.curuto.footballdata.R
import com.curuto.footballdata.databinding.ActivityMainBinding
import com.curuto.footballdata.model.Championship
import com.curuto.footballdata.utils.logD
import com.curuto.footballdata.view.main_activity.adapter.ChampionshipAdapter
import com.curuto.footballdata.viewModel.ChampionshipViewModel
import io.realm.OrderedRealmCollection
import io.realm.Realm
import javax.inject.Inject

class MainActivity : AppCompatActivity(), View.OnClickListener {

    
    @Inject lateinit var championshipAdapter : ChampionshipAdapter
    @Inject lateinit var championship: Championship
    @Inject lateinit var championshipViewModel: ChampionshipViewModel

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //FootballDataApllication usa o compoente para ir injetando módulos nas atividades,
        //talvez Componentes diferentes devem ser usados em activities diferentes
        (applicationContext as FootballDataApplication).myComp.inject(this)

        binding.rvChampionshipList.adapter = championshipAdapter
        binding.rvChampionshipList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        logD(championshipViewModel.retText())
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
}