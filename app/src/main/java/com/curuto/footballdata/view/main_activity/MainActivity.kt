package com.curuto.footballdata.view.main_activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

    val permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    val requestCode = 9999

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Deixando de criar componente na application, e criando na activity
        val myComp = DaggerFootballDataApplicationComponent.builder()
                        .championshipAdapterModule(ChampionshipAdapterModule(downloadChampionshipData(), onRowItemClicked()))
                        .build()

        myComp.inject(this)

        binding.rvChampionshipList.adapter = championshipAdapter
        binding.rvChampionshipList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        logD("Build sdk: "+ Build.VERSION.SDK_INT)


        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            // A permissão já foi concedida anteriormente
            // Faça a ação necessária aqui, como ler ou escrever um arquivo
        } else {
            // A permissão ainda não foi concedida
            // Solicite a permissão ao usuário
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        }

        //binding.acbAddChampionship.setOnClickListener(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestCode && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // A permissão foi concedida pelo usuário
            // Faça a ação necessária aqui, como ler ou escrever um arquivo
            showSnackbar(binding.root, "ALLOWED", Snackbar.LENGTH_SHORT)
        } else {
            showSnackbar(binding.root, "DENIED", Snackbar.LENGTH_SHORT)
        }
    }

    override fun onClick(p0: View?) {
        /*when(p0?.id){
            R.id.acb_add_championship -> {
                championshipViewModel.addChampionship("SUPER LIGA VIEW MODEL")
                logD("CLICKED ADD CHAMPS")
            }
        }*/
    }


    private fun downloadChampionshipData(): OnRowClicked {
        return object : OnRowClicked {
            override fun onPositionClicked(index: Int) {
                val item = championshipAdapter.getItem(index)
                var downloadQueued = false

                if(item != null){
                    downloadQueued = championshipViewModel.donwloadChampionshipData(item, applicationContext)
                }

                if(downloadQueued){
                    showSnackbar(binding.llcChampionshipListRoot, "Baixando todos dados", Snackbar.LENGTH_SHORT)
                }
            }
        }
    }

    private fun onRowItemClicked(): OnRowClicked {
        return object : OnRowClicked {
            override fun onPositionClicked(index: Int) {
                val item = championshipAdapter.getItem(index)

                val intent = Intent(this@MainActivity, SeasonActivity::class.java)
                intent.putExtra(EXTRA_ID, item?.id.toString())

                startActivity(intent)
            }
        }
    }
}