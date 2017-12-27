package com.colin.doumovie.view

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.colin.doumovie.R

/**
 * Created by tianweiping on 2017/12/18.
 */
abstract class BaseFragment : Fragment(), BaseView {

    var contentView: View? = null
    var loadLayout: View? = null;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        contentView = inflater?.inflate(R.layout.fragment_base, container, false)
        (contentView?.findViewById(R.id.fragment_base_layout) as ViewGroup).addView(inflater?.inflate(getLayoutId(), null), 0)
        loadLayout = contentView?.findViewById(R.id.base_loading_layout)
        initView()
        initEvent()
        return contentView
    }

    abstract fun getLayoutId(): Int


    override fun initEvent() {
    }

    /**
     * 显示一个loading
     *
     */
    override fun showLoading() {
        loadLayout?.visibility = View.VISIBLE
    }

    /**
     * 关闭loading
     */
    override fun dismissLoading() {
        loadLayout?.visibility = View.GONE
    }

    /**
     * 弹一个吐司提示
     *
     * @param code code
     * @param msg  提示信息
     */
    override fun showTipMessage(code: Int, msg: String) {
        Snackbar.make(contentView!!, msg, Snackbar.LENGTH_SHORT).show()
    }

    /**
     * 管理 网络请求生命周期的 key
     *
     * @return key
     */
    override fun getNetKey(): String {
        return javaClass.simpleName
    }

    /**
     * 因为token相关错误需要跳转到登录页面
     */
    override fun toLoginActBySessionError() {
    }

    override fun getContext(): Context {
        return activity
    }

    fun getColorByContext(colorId: Int): Int {

        return ContextCompat.getColor(context, colorId)
    }
}