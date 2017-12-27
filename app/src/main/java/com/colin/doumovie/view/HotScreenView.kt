package com.colin.doumovie.view

import android.view.View
import com.colin.doumovie.bean.HotScreenResult

/**
 * Created by tianweiping on 2017/12/18.
 * 热门上映
 */
interface HotScreenView : BaseView {

    fun notifyList(data:HotScreenResult)

    fun toMovieDetailAct(position:Int,shareView: View)
}