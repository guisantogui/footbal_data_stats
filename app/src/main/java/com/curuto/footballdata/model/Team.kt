package com.curuto.footballdata.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*
import javax.inject.Inject

@RealmClass
open class Team(@PrimaryKey var id: UUID, var name: String) : RealmModel {

    @Inject
    constructor(): this(UUID.randomUUID(), "")
}