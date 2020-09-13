package com.hiepnt.basemvp.ui.activity.main

import com.hiepnt.basemvp.base.BasePresenter
import com.hiepnt.basemvp.network.PostApi
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainPresenter (mainView: MainView) : BasePresenter<MainView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}