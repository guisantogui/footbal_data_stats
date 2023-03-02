package com.curuto.footballdata.services.csvParser.csvModels

abstract class CSVModel {

    open var id: Int = -1
    open var columnModelList = listOf("")

    fun matchDownloadedModel(downloadedColumns: Array<String>) : Boolean {
        return columnModelList.all { downloadedColumns.contains(it) }
    }
}