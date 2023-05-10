package com.curuto.footballdata.view.championship_detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.curuto.footballdata.databinding.RowItemTeamBinding
import com.curuto.footballdata.model.Team


class TeamViewHolder(itemView: RowItemTeamBinding) : RecyclerView.ViewHolder(itemView.root) {


    val tvTeam = itemView.tvTeamName

    fun bind(team: Team){
        tvTeam.text = team.name
    }

}