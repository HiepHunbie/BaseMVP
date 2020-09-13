package com.hiepnt.basemvp.base
import android.app.Application
import android.content.Context


public class MyApplication : Application() {

    private var instance: MyApplication? = null
    private var context : Context? = null

    /**
     * Credit by https://stackoverflow.com/a/43318117/3455160
     */
    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getInstance(): MyApplication? {
        return instance
    }

    fun setContext(context: Context){
        this.context = context
    }
    fun getContext(): Context? {
        return this.context
    }
}