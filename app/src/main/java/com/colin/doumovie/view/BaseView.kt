package com.colin.doumovie.view

import android.content.Context

/**
 * Created by tianweiping on 2017/12/15.
 */
interface BaseView {
    /**
     *  初始化view
     */
    fun initView()


    fun initEvent();

    /**
     * 显示一个loading
     *
     */
    fun showLoading()

    /**
     * 关闭loading
     */
    fun dismissLoading()

    /**
     * 弹一个吐司提示
     *
     * @param code code
     * @param msg  提示信息
     */
    fun showTipMessage(code: Int, msg: String)


    /**
     * 获得上下文对象
     *
     * @return 上下文对象
     */
    fun getContext(): Context

    /**
     * 管理 网络请求生命周期的 key
     *
     * @return key
     */
    fun getNetKey(): String

    /**
     * 因为token相关错误需要跳转到登录页面
     */
    fun toLoginActBySessionError()
}