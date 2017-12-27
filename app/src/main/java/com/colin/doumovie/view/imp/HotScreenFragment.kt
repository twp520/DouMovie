package com.colin.doumovie.view.imp

import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.colin.doumovie.R
import com.colin.doumovie.adapter.HotScreenListAdapter
import com.colin.doumovie.bean.HotScreenResult
import com.colin.doumovie.presenter.HotScreenPresenter
import com.colin.doumovie.presenter.imp.HotScreenPresenterImp
import com.colin.doumovie.view.BaseFragment
import com.colin.doumovie.view.HotScreenView

/**
 * Created by tianweiping on 2017/12/18.
 */
class HotScreenFragment : BaseFragment(), HotScreenView {

    private var mAdapter: HotScreenListAdapter? = null
    private var mData: MutableList<HotScreenResult.SubjectsBean> = mutableListOf()
    private var presenter: HotScreenPresenter? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_hot_screen_layout
    }

    private var rcView: RecyclerView? = null
//    private var refreshView: SwipeRefreshLayout? = null


    /**
     *  初始化view
     */
    override fun initView() {
        rcView = contentView?.findViewById(R.id.hot_rcView) as RecyclerView?
//        refreshView = contentView?.findViewById(R.id.hot_refresh) as SwipeRefreshLayout?
//        refreshView?.setColorSchemeColors(getColorByContext(R.color.refreshColor1),
//                getColorByContext(R.color.refreshColor2),
//                getColorByContext(R.color.refreshColor3))
        mAdapter = HotScreenListAdapter(activity, mData)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcView?.layoutManager = layoutManager
        rcView?.adapter = mAdapter
        presenter = HotScreenPresenterImp(this)
        presenter?.onAttach(context)
        presenter?.loadData(0)
    }

    override fun initEvent() {
        mAdapter?.setOnItemClickListener { _, position, shareView ->
            toMovieDetailAct(position, shareView)
        }

        mAdapter?.setOnItemBtnClickListener { _, _ ->
            showTipMessage(1, "暂时不能买票")
        }

//        refreshView?.setOnRefreshListener {
//            presenter?.loadData(0)
//        }
    }

    override fun notifyList(data: HotScreenResult) {
        mData.clear()
        mData.addAll(data.getSubjects()!!)
        mAdapter?.notifyDataSetChanged()
    }

    override fun toMovieDetailAct(position: Int, shareView: View) {
        val intent = Intent(activity, MovieDetailAct::class.java)
        intent.putExtra("id", mData[position].id)
        val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                shareView,
                getString(R.string.t_movie_list_to_detail))
        startActivity(intent, optionsCompat.toBundle())
    }
}