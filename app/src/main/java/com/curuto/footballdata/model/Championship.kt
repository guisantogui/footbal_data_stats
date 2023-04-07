package com.curuto.footballdata.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*
import javax.inject.Inject

@RealmClass
open class Championship constructor (
    @PrimaryKey var id: UUID,
    var name: String,
    var code: String,
    var seasonList: RealmList<Season>,) : RealmObject() {

    @Inject
    constructor(): this(UUID.randomUUID(),"","", RealmList()){
        seasonList.addAll(RealmList())
    }

}