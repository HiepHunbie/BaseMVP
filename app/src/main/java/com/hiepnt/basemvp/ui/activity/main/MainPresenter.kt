package com.hiepnt.basemvp.ui.activity.main

import android.content.Context
import com.hiepnt.basemvp.R
import com.hiepnt.basemvp.base.BasePresenter
import com.hiepnt.basemvp.network.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class MainPresenter (mainView: MainView) : BasePresenter<MainView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }

    fun getListData(context: Context) {
        subscription = postApi
            .getListData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { }
            .subscribe(
                {postList ->
                    view.getDataSuccess(postList)
                },
                {
                        error ->
                    view.getDatarError("ERROR")
                }
            )

    }
}