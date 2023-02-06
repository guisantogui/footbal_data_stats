package com.curuto.footballdata.model

import dagger.Module
import dagger.Provides
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import javax.inject.Inject

@RealmClass
open class Championship @Inject constructor (
            var name: String,
            var dataUrl: String,
            @PrimaryKey var id: String
            ) : RealmObject() {

    constructor(): this("","", "")
}


@Module
open class ChampionshipModule {

    @Provides
    open fun getEmptyChampionship(): Championship{
        return Championship("", "","")
    }
}