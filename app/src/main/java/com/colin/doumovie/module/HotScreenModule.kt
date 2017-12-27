package com.colin.doumovie.module

import com.colin.doumovie.bean.HotScreenResult

/**
 * Created by tianweiping on 2017/12/18.
 */
interface HotScreenModule : BaseModule {

    fun loadHotScreenData(page: Int, next: (result: HotScreenResult) -> Unit, error: (th: Throwable) -> Unit)
}