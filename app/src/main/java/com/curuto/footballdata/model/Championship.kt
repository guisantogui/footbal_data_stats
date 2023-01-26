package com.curuto.footballdata.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass
open class Championship (
            var name: String = "",
            @PrimaryKey var id: String = UUID.randomUUID().toString()) : RealmObject()
{}