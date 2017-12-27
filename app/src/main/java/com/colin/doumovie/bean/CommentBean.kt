package com.colin.doumovie.bean

/**
 * Created by tianweiping on 2017/12/26.
 */
class CommentBean {

    var count: Int = 0

    var comments: List<CommentListBean>? = null

    class CommentListBean {
        var rating: RatingInfo? = null
        var author: AuthorInfo? = null
        var content: String = ""
    }

    class RatingInfo {
        var value: Int = 0
    }

    class AuthorInfo {
        var name: String = ""
        var avatar: String = ""
    }

}