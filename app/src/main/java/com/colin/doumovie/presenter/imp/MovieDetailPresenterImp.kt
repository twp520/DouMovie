package com.colin.doumovie.presenter.imp

import android.content.Context
import com.colin.doumovie.module.MovieDetailModule
import com.colin.doumovie.module.imp.MovieDetailModuleImp
import com.colin.doumovie.presenter.MovieDetailPresenter
import com.colin.doumovie.view.MovieDetailView
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by tianweiping on 2017/12/20.
 */
class MovieDetailPresenterImp(var view: MovieDetailView?) : MovieDetailPresenter {


    private var module: MovieDetailModule? = null

    override fun onAttach(context: Context) {
        module = MovieDetailModuleImp()
    }

    override fun onDestroy() {
        view = null
        module = null
    }

    override fun loadData(id: String) {
        if (view == null || module == null)
            return
        //下载详情信息
        module?.loadDataById(id)
                ?.subscribeBy(
                        onNext = {
                            //设置头部的信息
                            view?.setHeadInfo(it.title, it.images?.medium, it.comments_count)
                            //设置详细信息
                            view?.setMovieInfo(module?.getYearAndGenres(it.year, it.genres!!)!!,
                                    module?.safeGetPubData(it.pubdates!!)!!,
                                    if (it.original_title!!.isNullOrEmpty()) {
                                        it.title!!
                                    } else {
                                        it.original_title!!
                                    },
                                    it.durations!![0],
                                    it.rating!!, it.ratings_count, it.summary!!)
                            view?.initRatingFragment(it.rating!!, it.photos!![0].image, it.title)
                            //设置影人
                            view?.setActorListInfo(module?.getActorAndDirectorsList(it.directors!!, it.casts!!))
                            //设置剧照
                            view?.setMoviePhotoListInfo(it.photos!!)

                        },
                        onError = {
                            it.printStackTrace()
                            view?.showTipMessage(1, "网络异常")
                        }
                )
        //下载影评信息
        module?.loadCommentDataById(id)
                ?.subscribeBy(onNext = {
                    view?.setMovieCommentListInfo(it.comments)
                }, onError = {
                    view?.showTipMessage(1, "网络异常")
                })
    }
}