package com.colin.doumovie.module.imp

import com.colin.doumovie.api.BuildApi
import com.colin.doumovie.bean.CastsBean
import com.colin.doumovie.bean.CommentBean
import com.colin.doumovie.bean.MovieDetailResult
import com.colin.doumovie.module.BaseModuleImp
import com.colin.doumovie.module.MovieDetailModule
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by tianweiping on 2017/12/20.
 */
class MovieDetailModuleImp : BaseModuleImp(), MovieDetailModule {

    override fun loadDataById(id: String): Observable<MovieDetailResult>? {
        val parm = HashMap<String, String>()
        parm.put("apikey", getApiKey())
        parm.put("city", "深圳")
        parm.put("client", "")
        parm.put("udid", getToken())
        return BuildApi.buildApiServers()
                ?.getMovieDetailById(id, parm)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())

    }

    override fun loadCommentDataById(id: String): Observable<CommentBean>? {
        val parm = HashMap<String, String>()
        parm.put("apikey", getApiKey())
        parm.put("city", "深圳")
        parm.put("start", "0")
        parm.put("count", "5")
        parm.put("client", "")
        parm.put("udid", getToken())
        return BuildApi.buildApiServers()
                ?.getMovieDetailCommentById(id, parm)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
    }

    override fun getYearAndGenres(year: String?, genres: List<String?>): String {
        val builder = StringBuilder()
        genres.forEach {
            builder.append(it + " / ")
        }
        builder.delete(builder.length - 2, builder.length)
        return year + " / " + builder.toString()
    }

    override fun safeGetPubData(pubdatas: List<String>): String {
        if (pubdatas.isNotEmpty()) {
            pubdatas.forEach {
                if (it.contains("中国"))
                    return it
            }
        }
        return ""
    }

    override fun getActorAndDirectorsList(directors: List<CastsBean>, actors: List<CastsBean>): MutableList<CastsBean> {
        val result: MutableList<CastsBean> = mutableListOf()
        directors.forEach {
            it.type = 1
            result.add(it)
        }
        result.addAll(actors)
        return result
    }
}