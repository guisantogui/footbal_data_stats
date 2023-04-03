package com.curuto.footballdata.repository.realm

import dagger.Module
import dagger.Provides
import io.realm.Realm

@Module
class RealmModule {

    @Provides
    fun getRealmInstance(): Realm {
        return Realm.getDefaultInstance();
    }
}
