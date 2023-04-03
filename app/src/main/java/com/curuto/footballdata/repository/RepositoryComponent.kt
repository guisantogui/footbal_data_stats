package com.curuto.footballdata.repository

import com.curuto.footballdata.services.csvParser.csvModels.CSVModel
import dagger.Component

@Component
interface RepositoryComponent {

    fun inject(csvModel: CSVModel)

}