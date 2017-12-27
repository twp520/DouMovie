package com.colin.doumovie.bean

/**
 * Created by tianweiping on 2017/12/19.
 */
class MovieDetailResult {

    var rating: RatingBean? = null//评分信息
    var images: ImagesBean? = null //头部的海报图
    var year: String? = null //年份
    var id: String? = null //id
    var mobile_url: String? = null //分享出去的链接
    var photos_count: Int = 0 //剧照数量
    var title: String? = null //影片标题
    var original_title: String? = null //影片原名
    var share_url: String? = null//分享url
    var summary: String? = null//简介
    var reviews_count: Int = 0//热议
    var comments_count: Int = 0//热议1
    var ratings_count: Int = 0//评分数量
    var writers: List<WritersBean>? = null//编剧们
    var pubdates: List<String>? = null//上映地区
    var tags: List<String>? = null //想看的标签
    var durations: List<String>? = null//时长
    var genres: List<String>? = null//分类
    var trailer_urls: List<String>? = null
    var casts: MutableList<CastsBean>? = null//演员们
    var photos: List<PhotosBean>? = null
    var directors: List<CastsBean>? = null//导演们


    class WritersBean {

        var avatars: AvatarsBean? = null
        var name_en: String? = null
        var name: String? = null
        var alt: String? = null
        var id: String? = null

        class AvatarsBean {
            var small: String? = null
            var large: String? = null
            var medium: String? = null
        }
    }

    class PhotosBean {

        var thumb: String? = null
        var image: String? = null
        var cover: String? = null
        var alt: String? = null
        var id: String? = null
        var icon: String? = null
    }
}