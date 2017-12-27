package com.colin.doumovie.module

import com.colin.doumovie.api.BaseURL

/**
 * Created by tianweiping on 2017/12/15.
 */
open class BaseModuleImp : BaseModule {
    override fun getApiKey(): String {
        return BaseURL.KEY
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    override fun getCurrentTime(): String {
        return ""
    }

    /**
     * 获取token
     *
     * @return token
     */
    override fun getToken(): String {
        return ""
    }
}