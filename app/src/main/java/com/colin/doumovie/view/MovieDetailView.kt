package com.colin.doumovie.view

import com.colin.doumovie.bean.CastsBean
import com.colin.doumovie.bean.CommentBean
import com.colin.doumovie.bean.MovieDetailResult
import com.colin.doumovie.bean.RatingBean

/**
 * Created by tianweiping on 2017/12/19.
 */
interface MovieDetailView : BaseView {

    fun setHeadInfo(title: String?, imageUrl: String?, hotTalk: Int)

    fun setMovieInfo(yearAndGenres: String, pubdata: String, original_title: String,
                     dur: String, rating: RatingBean, ratingCount: Int, summary: String)

    fun setActorListInfo(data: MutableList<CastsBean>?)

    fun setMoviePhotoListInfo(data: List<MovieDetailResult.PhotosBean>?)

    fun initRatingFragment(rating: RatingBean, imageUrl: String?, title: String?)

    fun setMovieCommentListInfo(data: List<CommentBean.CommentListBean>?)
}