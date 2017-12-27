package com.colin.doumovie.view.imp

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.colin.doumovie.R
import com.colin.doumovie.adapter.ActorListAdapter
import com.colin.doumovie.adapter.MovieCommentListAdapter
import com.colin.doumovie.adapter.MoviePhotoListAdapter
import com.colin.doumovie.bean.CastsBean
import com.colin.doumovie.bean.CommentBean
import com.colin.doumovie.bean.MovieDetailResult
import com.colin.doumovie.bean.RatingBean
import com.colin.doumovie.presenter.MovieDetailPresenter
import com.colin.doumovie.presenter.imp.MovieDetailPresenterImp
import com.colin.doumovie.view.MovieDetailView
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.movie_detail_info_layout.*

/**
 * 特殊的activity所以不继承
 */
class MovieDetailAct : AppCompatActivity(), MovieDetailView {

    private var presenter: MovieDetailPresenter? = null
    private var loading: ProgressDialog? = null

    private var actorAdapter: ActorListAdapter? = null
    private var photosAdapter: MoviePhotoListAdapter? = null
    private var commentAdapter: MovieCommentListAdapter? = null

    private var ratingFragment: MovieDetailRatingFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        postponeEnterTransition()
        initView()
        initEvent()
    }

    override fun initView() {
        setSupportActionBar(movie_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        loading = ProgressDialog(getContext())
        ratingFragment = MovieDetailRatingFragment()
        loading?.setMessage("正在加载中...")
        presenter = MovieDetailPresenterImp(this)
        presenter?.onAttach(this)
        presenter?.loadData(intent.getStringExtra("id"))

    }

    override fun initEvent() {
        movie_detail_toolbar.setNavigationOnClickListener { supportFinishAfterTransition() }
        movie_detail_rating_layout.setOnClickListener {
            ratingFragment?.show(supportFragmentManager, "ratingFragment")
        }
        movie_detail_hot_talk.setOnClickListener {
            showTipMessage(1, "热议")
        }
        movie_detail_btn_watch.setOnClickListener { showTipMessage(1, "想看") }
        movie_detail_btn_watched.setOnClickListener { showTipMessage(1, "看过") }
        movie_detail_ticket.setOnClickListener { showTipMessage(1, "买票") }
    }

    override fun setHeadInfo(title: String?, imageUrl: String?, hotTalk: Int) {
        movie_detail_coll_layout.title = title
        movie_detail_hot_talk.text = getString(R.string.movie_detail_hot_talk).format(hotTalk)
        val target = object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
                movie_detail_coll_head_photo.setImageBitmap(resource)
                startPostponedEnterTransition()
                Palette.from(resource).generate {
                    if (it != null) {
                        val dvs = it.darkVibrantSwatch
                        val lvs = it.lightVibrantSwatch
                        val dms = it.darkMutedSwatch
                        val lms = it.lightMutedSwatch
                        var color = when {
                            dvs?.rgb != null -> dvs.rgb
                            lvs?.rgb != null -> lvs.rgb
                            dms?.rgb != null -> dms.rgb
                            lms?.rgb != null -> lms.rgb
                            else -> ContextCompat.getColor(getContext(), R.color.themColor)
                        }
//                        Log.e("twp", "lightMutedSwatch = ${it.lightMutedSwatch?.rgb} ")
//                        Log.e("twp", "darkMutedSwatch = ${it.darkMutedSwatch?.rgb}")
//                        Log.e("twp", "lightVibrantSwatch = ${it.lightVibrantSwatch?.rgb}")
//                        Log.e("twp", "darkVibrantSwatch = ${it.darkVibrantSwatch?.rgb}")
                        window.statusBarColor = color
                        movie_detail_coll_head_bg.setBackgroundColor(color)
                        movie_detail_coll_layout.setContentScrimColor(color)
                    }
                }
            }
        }
        Glide.with(getContext())
                .load(imageUrl)
                .asBitmap()
                .into(target)
    }

    override fun setMovieInfo(yearAndGenres: String, pubdata: String, original_title: String,
                              dur: String, rating: RatingBean, ratingCount: Int, summary: String) {
        movie_detail_info_genres.text = yearAndGenres
        movie_detail_info_original_title.text = "原名：$original_title"
        movie_detail_info_pubdata.text = "上映时间：$pubdata"
        movie_detail_info_durations.text = "片长：$dur"
        movie_detail_rating_rating.rating = (rating.average / 2f).toFloat()
        movie_detail_rating_tv.text = rating.average.toString()
        movie_detail_rating_count.text = "$ratingCount 人"
        movie_detail_summary.text = summary
        rating.count = ratingCount
        if (0 == rating.average.toInt()) {
            movie_detail_rating_layout.isEnabled = false
            movie_detail_rating_count.text = "暂无评分"
        }
    }

    override fun setActorListInfo(data: MutableList<CastsBean>?) {
        actorAdapter = ActorListAdapter(getContext(), data!!)
        val layoutManager = LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        movie_detail_actor_list.layoutManager = layoutManager
        movie_detail_actor_list.adapter = actorAdapter
    }

    override fun setMoviePhotoListInfo(data: List<MovieDetailResult.PhotosBean>?) {
        photosAdapter = MoviePhotoListAdapter(getContext(), data!!)
        val layoutManager = LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        movie_detail_photo_list.layoutManager = layoutManager
        movie_detail_photo_list.adapter = photosAdapter
    }

    override fun setMovieCommentListInfo(data: List<CommentBean.CommentListBean>?) {
        Log.e("twp", "-----setMovieCommentListInfo-----")
        commentAdapter = MovieCommentListAdapter(getContext(), data!!)
        val layoutManager = LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)
        movie_detail_comment_list.layoutManager = layoutManager
        movie_detail_comment_list.adapter = commentAdapter
    }

    override fun initRatingFragment(rating: RatingBean, imageUrl: String?, title: String?) {
        val args = Bundle()
        args.putParcelable("rating", rating)
        args.putString("imageUrl", imageUrl)
        args.putString("title", title)
        ratingFragment?.arguments = args
    }

    override fun showLoading() {
        loading?.show()
    }

    override fun dismissLoading() {
        loading?.dismiss()
    }

    override fun showTipMessage(code: Int, msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    }

    override fun getContext(): Context {
        return this
    }

    override fun getNetKey(): String {
        return javaClass.simpleName
    }

    override fun toLoginActBySessionError() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }
}
