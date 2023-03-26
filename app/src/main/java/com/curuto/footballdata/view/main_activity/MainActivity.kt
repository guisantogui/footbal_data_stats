package com.curuto.footballdata.view.main_activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.curuto.footballdata.DaggerFootballDataApplicationComponent
import com.curuto.footballdata.R
import com.curuto.footballdata.databinding.ActivityMainBinding
import com.curuto.footballdata.utils.*
import com.curuto.footballdata.view.custom.OnRowClicked
import com.curuto.footballdata.view.main_activity.adapter.ChampionshipAdapter
import com.curuto.footballdata.view.main_activity.adapter.ChampionshipAdapterModule
import com.curuto.footballdata.view.season_activity.SeasonActivity
import com.curuto.footballdata.view.main_activity.view_model.ChampionshipViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainActivity : AppCompatActivity(), View.OnClickListener {


    @Inject lateinit var championshipAdapter : ChampionshipAdapter
    @Inject lateinit var championshipViewModel: ChampionshipViewModel

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Deixando de criar componente na application, e criando na activity
        val myComp = DaggerFootballDataApplicationComponent.builder()
                        .championshipAdapterModule(ChampionshipAdapterModule(downloadChampionshipData(), onRowItemClicked()))
                        .build()

        myComp.inject(this)

        binding.rvChampionshipList.adapter = championshipAdapter
        binding.rvChampionshipList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        logD("Build sdk: "+ Build.VERSION.SDK_INT)

        if (!hasStoragePermission(this)) {
            requestStoragePermission(this, STORAGE_PERMISSION_CODE)
        }

        binding.acbGrantPermission.setOnClickListener(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestCode && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            binding.rvChampionshipList.show()
            binding.ivBgWarningIcon.hardHide()
            binding.acbGrantPermission.hardHide()
            binding.tvWarningMessage.hardHide()

        } else {
            binding.rvChampionshipList.hardHide()
            binding.ivBgWarningIcon.show()
            binding.acbGrantPermission.show()
            binding.tvWarningMessage.show()
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.acb_grant_permission -> {
                requestStoragePermission(this, STORAGE_PERMISSION_CODE)
            }
        }
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