package com.hiepnt.basemvp.ui.activity.main

import android.media.MediaPlayer.*
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import android.widget.MediaController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hiepnt.basemvp.R
import com.hiepnt.basemvp.base.BaseActivity
import com.hiepnt.basemvp.model.Children
import com.hiepnt.basemvp.model.DataListDetail
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainPresenter>(), MainView {

    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }

    private var mainTitleAdapter: MainTitleAdapter? = null
    private var mainDetailAdapter: MainDetailAdapter? = null
    private var countCurrentVideo = 0
    private var listVideoMain: ArrayList<String> = ArrayList()
    private var timer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prg_loading.visibility = View.VISIBLE
        presenter!!.getListData(this)


        mainTitleAdapter = MainTitleAdapter(this!!, { itemDto: Children, position: Int ->
            mainDetailAdapter!!.updatePosts(itemDto.children, false)
        })
        ryv_title!!.adapter = mainTitleAdapter
        ryv_title!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mainDetailAdapter = MainDetailAdapter(this!!, { itemDto: Children, position: Int ->
            if(itemDto.videosLinks.size>0){
                playvideoDetail(itemDto.videosLinks[0])
            }
        })
        ryv_detail!!.adapter = mainDetailAdapter
        ryv_detail!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        vdv_main.setOnClickListener {
            layout_detail.visibility = View.VISIBLE
            vdv_main.visibility = View.GONE
            timer!!.start()
        }


        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                restartScreen()
            }
        }

        layout_detail.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        timer!!.cancel()
                        timer!!.start()
                    }
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

    }

    fun restartScreen() {
        timer!!.cancel()
        layout_detail.visibility = View.GONE
        vdv_main.visibility = View.VISIBLE
        countCurrentVideo = 0
        playvideo(listVideoMain[countCurrentVideo])
    }

    override fun onBackPressed() {
        super.onBackPressed()
//        restartScreen()
    }


    fun playvideoDetail(videoUrl: String) {
        if(videoUrl.isNotEmpty()){
            prg_detail.visibility = View.VISIBLE
            vdv_detail.setVideoURI(Uri.parse(videoUrl))
            vdv_detail.requestFocus()
            timer!!.cancel()

            vdv_detail.setOnCompletionListener(OnCompletionListener {
                timer!!.start()
            })

            vdv_detail.setOnPreparedListener(OnPreparedListener { mediaPlayer ->
                prg_detail.visibility = View.GONE
                vdv_detail.start()
                mediaPlayer.setOnVideoSizeChangedListener { mp, width, height ->
                    val mediaController = MediaController(this)
                    vdv_detail.setMediaController(mediaController)
                    mediaController.setAnchorView(vdv_main)
                }
            })

            vdv_detail.setOnErrorListener(OnErrorListener { mediaPlayer, i, i1 -> false })
        }

    }

    fun playvideo(videoUrl: String) {
        if(videoUrl.isNotEmpty()) {
            prg_loading.visibility = View.VISIBLE
            vdv_main.setVideoURI(Uri.parse(videoUrl))
            vdv_main.requestFocus()

            vdv_main.setOnCompletionListener(OnCompletionListener {
                if (countCurrentVideo < listVideoMain.size - 1) {
                    countCurrentVideo += 1
                } else {
                    countCurrentVideo = 0
                }
                playvideo(listVideoMain[countCurrentVideo])
            })

            vdv_main.setOnPreparedListener(OnPreparedListener { mediaPlayer ->
                vdv_main.start()
                prg_loading.visibility = View.GONE
                mediaPlayer.setOnVideoSizeChangedListener { mp, width, height ->
                    val mediaController = MediaController(this)
                    vdv_main.setMediaController(mediaController)
                    mediaController.setAnchorView(vdv_main)
                }
            })

            vdv_main.setOnErrorListener(OnErrorListener { mediaPlayer, i, i1 -> false })
        }
    }

    override fun getDataSuccess(dataResult: DataListDetail) {
        countCurrentVideo = 0
        listVideoMain.clear()
        listVideoMain.addAll(dataResult.videosLinks)
        playvideo(listVideoMain[countCurrentVideo])
        mainTitleAdapter!!.updatePosts(dataResult.children, false)
    }

    override fun getDatarError(dataResult: String) {
    }
}
