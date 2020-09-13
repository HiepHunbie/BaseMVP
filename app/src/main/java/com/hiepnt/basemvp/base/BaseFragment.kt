package com.hiepnt.basemvp.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import com.hiepnt.basemvp.ui.activity.main.MainActivity
abstract class BaseFragment<P : BasePresenter<BaseView>>  : androidx.fragment.app.Fragment(), MVPView {

    var parentActivity: MainActivity? = null
    private var progressDialog: ProgressDialog? = null
    open var presenter: P? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            val activity = context as MainActivity?
            this.parentActivity = activity
//            activity?.onFragmentAttached()
        }
        this.retainInstance = retainInstance()
        presenter = instantiatePresenter()
    }

    private fun retainInstance(): Boolean {
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
//        performDependencyInjection()
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun hideProgress() {
        if (progressDialog != null && progressDialog?.isShowing!!) {
            progressDialog?.cancel()
        }
    }

    override fun showProgress() {
        hideProgress()
//        progressDialog = CommonUtil.showLoadingDialog(this.context)
    }

    fun getMainActivity() = parentActivity

    //    override fun onBackPressed() {}
//    private fun performDependencyInjection() = AndroidSupportInjection.inject(this)
    protected abstract fun instantiatePresenter(): P


    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.onViewDestroyed()
    }
}