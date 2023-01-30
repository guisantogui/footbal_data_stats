package com.curuto.footballdata.view.main_activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.curuto.footballdata.databinding.RowItemChampionshipBinding
import com.curuto.footballdata.model.Championship
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter


open class ChampionshipAdapter
                constructor(var championshipData: OrderedRealmCollection<Championship>):
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

