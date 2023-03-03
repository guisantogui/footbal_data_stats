package com.curuto.footballdata.services.csvParser.csvModels

import com.curuto.footballdata.model.Match

abstract class CSVModel {

    open var columnModelList = listOf("")

    fun matchDownloadedModel(downloadedColumns: Array<String>) : Boolean {
        return columnModelList.all { downloadedColumns.contains(it) }
    }

    abstract fun readLine(array: Array<String>): Match
}