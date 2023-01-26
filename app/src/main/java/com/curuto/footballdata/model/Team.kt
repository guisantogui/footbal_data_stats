package com.curuto.footballdata.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey

data class Team(@PrimaryKey val id: Int, val name: Int) : RealmModel {
}