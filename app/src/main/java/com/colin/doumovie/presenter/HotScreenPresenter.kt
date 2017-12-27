package com.colin.doumovie.presenter

/**
 * Created by tianweiping on 2017/12/18.
 */
interface HotScreenPresenter : BasePresenter {
    /**
     * 加载数据
     * @param page 分页
     */
    fun loadData(page: Int)
}