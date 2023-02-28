package com.curuto.footballdata.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass
open class Team(@PrimaryKey val id: UUID, val name: String) : RealmModel {
}