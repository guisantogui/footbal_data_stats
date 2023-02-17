package com.curuto.footballdata.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import javax.inject.Inject

@RealmClass
open class Championship constructor (
            var name: String,
            var dataUrl: String,
            @PrimaryKey var id: String
            ) : RealmObject() {

    @Inject
    constructor(): this("","", "")


    fun text(): String {
        return "champs returned"
    }
}