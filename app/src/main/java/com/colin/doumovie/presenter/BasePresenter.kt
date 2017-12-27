package com.colin.doumovie.presenter

import android.content.Context

/**
 * Created by tianweiping on 2017/12/15.
 */
interface BasePresenter {
    /**
     * 与view绑定
     *
     * @param context
     */
    fun onAttach(context: Context)

    /**
     * 销毁
     */
    fun onDestroy()
}