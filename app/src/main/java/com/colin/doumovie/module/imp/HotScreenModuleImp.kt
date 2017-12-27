package com.colin.doumovie.module.imp

import com.colin.doumovie.api.BuildApi
import com.colin.doumovie.bean.HotScreenResult
import com.colin.doumovie.module.BaseModuleImp
import com.colin.doumovie.module.HotScreenModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * Created by tianweiping on 2017/12/18.
 */
class HotScreenModuleImp : BaseModuleImp(), HotScreenModule {

    override fun loadHotScreenData(page: Int, next: (result: HotScreenResult) -> Unit
                                   , error: (th: Throwable) -> Unit) {
        val parm = HashMap<String, String>()
        parm.put("apikey", getApiKey())
        parm.put("city", "深圳")
        parm.put("start", page.toString())
        parm.put("count", "10")
        parm.put("client", "")
        parm.put("udid", getToken())
        BuildApi.buildApiServers()!!
                .getHotScreenList(parm)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = next,
                        onError = error)
    }

}