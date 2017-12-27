package com.colin.doumovie.module

/**
 * Created by tianweiping on 2017/12/15.
 */
interface BaseModule {
    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    fun getCurrentTime(): String

    /**
     * 获取token
     *
     * @return token
     */
    fun getToken(): String

    /**
     * 获取豆瓣的api key
     */
    fun getApiKey():String
}