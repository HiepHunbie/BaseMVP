package com.hiepnt.basemvp.ui.activity.main

import com.hiepnt.basemvp.base.BaseView
import com.hiepnt.basemvp.model.DataListDetail

interface MainView : BaseView {
    fun getDataSuccess(dataResult: DataListDetail)
    fun getDatarError(dataResult: String)
}