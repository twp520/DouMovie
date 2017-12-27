package com.colin.doumovie.view.imp

import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.util.SparseArray
import com.colin.doumovie.R
import com.colin.doumovie.view.BaseActivity
import com.colin.doumovie.view.MainView
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainView {

    private var fragmentArray: SparseArray<Fragment>? = null
    private var titleArray: SparseArray<String>? = null

    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    /**
     *  初始化view
     */
    override fun initView() {
        setToolBarTitle("主页")
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        val dt = ActionBarDrawerToggle(this, main_draw, base_toolbar, R.string.open, R.string.close)
        dt.syncState()
        main_draw.addDrawerListener(dt)
        //初始化第一个fragment
        fragmentArray = SparseArray()
        titleArray = SparseArray()
        showLoading()
        val hotF = HotScreenFragment()
        supportFragmentManager.beginTransaction().add(R.id.main_content_layout, hotF, "f${R.id.main_menu_hot}").commit()
        fragmentArray?.put(R.id.main_menu_hot, hotF)
        titleArray?.put(R.id.main_menu_hot, getString(R.string.menu_hot))
        titleArray?.put(R.id.main_menu_sensor, getString(R.string.menu_sensor))
        titleArray?.put(R.id.main_menu_find, getString(R.string.menu_find))
        setToolBarTitle(getString(R.string.menu_hot))
        dismissLoading()
    }

    override fun initEvent() {
        main_navigation.setNavigationItemSelectedListener {
            switchFragment(it.itemId)
            true
        }
    }


    override fun switchFragment(id: Int) {
        var fragment = fragmentArray?.get(id)
        if (fragment == null) {
            fragment = when (id) {
                R.id.main_menu_hot -> {
                    HotScreenFragment()
                }
                R.id.main_menu_sensor -> {
                    SensorFragment()
                }
                else -> null
            }
        }
        if (fragment != null) {
            fragmentArray?.put(id, fragment)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content_layout, fragment, "f$id")
                    .commit()
            setToolBarTitle(titleArray!![id])
            main_draw.closeDrawers()
        }
    }

}
