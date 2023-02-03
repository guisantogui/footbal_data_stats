package com.curuto.footballdata.viewModel

import com.curuto.footballdata.DaggerFootbalDataApplicationComponent
import com.curuto.footballdata.model.Championship
import dagger.Module
import dagger.Provides
import io.realm.Realm
import java.util.*
import javax.inject.Inject

class ChampionshipViewModel {

    @Inject lateinit var realm: Realm

    init {
        val myComp = DaggerFootbalDataApplicationComponent.create()
        myComp.inject(this)
    }

    fun addChampionship(name: String){
        realm.executeTransactionAsync{
            it.insert(Championship(name, UUID.randomUUID().toString()))
        }
    }

    fun retText(): String{
        return "VIEWMODEL WORKING!!"
    }
}

@Module
class ChampionshipViewModelModule {

    @Provides
    fun getChampionshipViewModel(): ChampionshipViewModel{
        return ChampionshipViewModel()
    }
}