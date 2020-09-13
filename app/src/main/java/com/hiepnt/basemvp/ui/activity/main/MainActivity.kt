package com.hiepnt.basemvp.ui.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hiepnt.basemvp.R
import com.hiepnt.basemvp.base.BaseActivity
import java.io.Serializable

class MainActivity : BaseActivity<MainPresenter>(),MainView {

    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
