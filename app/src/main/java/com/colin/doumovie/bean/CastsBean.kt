package com.colin.doumovie.bean

/**
 * Created by tianweiping on 2017/12/19.
 *
 *
 * 影人实体类  包括导演和演员
 */

class CastsBean {

    /**
     * avatars : {"small":"https://img5.doubanio.com/img/celebrity/small/53186.jpg","large":"https://img5.doubanio.com/img/celebrity/large/53186.jpg","medium":"https://img5.doubanio.com/img/celebrity/medium/53186.jpg"}
     * name_en : Vin Diesel
     * name :  范·迪塞尔
     * alt : https://movie.douban.com/celebrity/1041020/
     * id : 1041020
     */

    var avatars: ImagesBean? = null
    var name_en: String? = null
    var name: String? = null
    var alt: String? = null
    var id: Int = 0
    var type: Int = 0 //导演还是演员， 默认0 是演员，1是导演
}
