package com.colin.doumovie.view

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.colin.doumovie.R
import kotlinx.android.synthetic.main.activity_base.*

/**
 * Created by tianweiping on 2017/12/15.
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        base_content_layout.addView(layoutInflater.inflate(getContentViewId(), null), 0)
        setSupportActionBar(base_toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        base_toolbar.setNavigationOnClickListener { finish() }
        initView()
        initEvent()
        initSome()
    }

    abstract fun getContentViewId(): Int

    override fun initEvent() {

    }

    open fun initSome() {}

    fun setToolBarTitle(title: String) {
        base_toolbar.title = title
    }

    /**
     * 显示一个loading
     */
    override fun showLoading() {
        base_loading_layout.visibility = View.VISIBLE
    }

    /**
     * 关闭loading
     */
    override fun dismissLoading() {
        base_loading_layout.visibility = View.GONE
    }

    /**
     * 弹一个吐司提示
     *
     * @param code code
     * @param msg  提示信息
     */
    override fun showTipMessage(code: Int, msg: String) {
        Snackbar.make(base_layout, msg, Snackbar.LENGTH_SHORT).show()

    }

    /**
     * 获得上下文对象
     *
     * @return 上下文对象
     */
    override fun getContext(): Context {
        return this
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
}