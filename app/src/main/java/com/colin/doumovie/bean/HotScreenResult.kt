package com.colin.doumovie.bean

/**
 * Created by tianweiping on 2017/12/19.
 */
class HotScreenResult {
    private var count: Int = 0
    private var start: Int = 0
    private var total: Int = 0
    private var title: String? = null
    private var subjects: MutableList<SubjectsBean>? = null

    fun getCount(): Int {
        return count
    }

    fun setCount(count: Int) {
        this.count = count
    }

    fun getStart(): Int {
        return start
    }

    fun setStart(start: Int) {
        this.start = start
    }

    fun getTotal(): Int {
        return total
    }

    fun setTotal(total: Int) {
        this.total = total
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getSubjects(): MutableList<SubjectsBean>? {
        return subjects
    }

    fun setSubjects(subjects: MutableList<SubjectsBean>) {
        this.subjects = subjects
    }

    class SubjectsBean {

        var rating: RatingBean? = null
        var title: String? = null
        var collect_count: Int = 0
        var images: ImagesBean? = null
        var id: String? = null
        var casts: List<CastsBean>? = null
        var directors: List<CastsBean>? = null
    }
}