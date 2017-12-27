package com.colin.doumovie.api

import com.colin.doumovie.bean.CommentBean
import com.colin.doumovie.bean.HotScreenResult
import com.colin.doumovie.bean.MovieDetailResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Created by tianweiping on 2017/12/18.
 */
interface ApiServers {

    /**
     * 获取正在上映的数据
     */
    @GET(BaseURL.HOTSCREEN)
    fun getHotScreenList(@QueryMap par: HashMap<String, String>): Observable<HotScreenResult>

    @GET("v2/movie/subject/{id}")
    fun getMovieDetailById(@Path("id") id: String,
                           @QueryMap par: HashMap<String, String>): Observable<MovieDetailResult>

    @GET("v2/movie/subject/{id}/comments")
    fun getMovieDetailCommentById(@Path("id") id: String,
                                  @QueryMap par: HashMap<String, String>): Observable<CommentBean>
}