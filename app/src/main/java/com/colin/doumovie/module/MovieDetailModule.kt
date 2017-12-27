package com.colin.doumovie.module

import com.colin.doumovie.bean.CastsBean
import com.colin.doumovie.bean.CommentBean
import com.colin.doumovie.bean.MovieDetailResult
import io.reactivex.Observable

/**
 * Created by tianweiping on 2017/12/19.
 */
interface MovieDetailModule : BaseModule {

    fun loadDataById(id: String): Observable<MovieDetailResult>?

    fun loadCommentDataById(id: String): Observable<CommentBean>?

    fun getYearAndGenres(year: String?, genres: List<String?>): String

    fun safeGetPubData(pubdatas: List<String>): String

    fun getActorAndDirectorsList(directors: List<CastsBean>, actors: List<CastsBean>): MutableList<CastsBean>
}