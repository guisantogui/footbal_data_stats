package com.curuto.footballdata.view.main_activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.curuto.footballdata.databinding.RowItemChampionshipBinding
import com.curuto.footballdata.model.Championship
import dagger.Module
import dagger.Provides
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.RealmRecyclerViewAdapter
import javax.inject.Inject


open class ChampionshipAdapter
        @Inject constructor(var championshipData: OrderedRealmCollection<Championship>):
                                    RealmRecyclerViewAdapter<Championship, ChampionshipViewHolder>
                                                                    (championshipData, true) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionshipViewHolder {
        val binding = RowItemChampionshipBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChampionshipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChampionshipViewHolder, position: Int) {
        holder.bind(championshipData[position])
    }
}

@Module
open class ChampionshipAdapterModule{

    @Provides
    open fun getEmptyAdapter(): ChampionshipAdapter {
        val all = Realm.getDefaultInstance().where(Championship::class.java).findAll()
        return ChampionshipAdapter(all)
    }
}
