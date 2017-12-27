package com.colin.doumovie.presenter.imp

import android.content.Context
import com.colin.doumovie.module.HotScreenModule
import com.colin.doumovie.module.imp.HotScreenModuleImp
import com.colin.doumovie.presenter.HotScreenPresenter
import com.colin.doumovie.view.HotScreenView

/**
 * Created by tianweiping on 2017/12/19.
 */
class HotScreenPresenterImp(var view: HotScreenView?) : HotScreenPresenter {
    private var module: HotScreenModule? = null

    override fun onAttach(context: Context) {
        module = HotScreenModuleImp()
    }

    override fun onDestroy() {
        module = null
        view = null
    }

    override fun loadData(page: Int) {
        if (view == null || module == null)
            return
        view?.showLoading()
        module?.loadHotScreenData(page, {
            if (view != null) {
                view!!.notifyList(it)
                view!!.dismissLoading()
            }
        }, {
            it.printStackTrace()
            view!!.dismissLoading()
            if (view != null)
                view!!.showTipMessage(1, "网络异常")
        })
    }


}