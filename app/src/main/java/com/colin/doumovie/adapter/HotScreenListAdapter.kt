package com.colin.doumovie.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.colin.doumovie.R
import com.colin.doumovie.bean.CastsBean
import com.colin.doumovie.bean.HotScreenResult
import com.colin.doumovie.util.Util
import com.colin.doumovie.view.imp.MovieDetailAct

/**
 * Created by tianweiping on 2017/12/18.
 */
class HotScreenListAdapter(private val context: Activity, private val data: MutableList<HotScreenResult.SubjectsBean>)
    : RecyclerView.Adapter<HotScreenListAdapter.ViewHolder>() {

    private lateinit var itemClick: (itemView: View, position: Int, shareView: View) -> Unit
    private lateinit var itemBtnClick: (view: View, position: Int) -> Unit

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_hot_screen_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.title?.text = data[position].title
        Glide.with(context).load(data[position].images?.small)
                .dontAnimate()
                .into(holder?.image)
        holder?.rating?.rating = (data[position].rating?.average!! / 2).toFloat()
        holder?.dy?.text = "导演：" + data[position].directors!![0].name
        holder?.watchNum?.text = Util.formatLargeNumber(data[position].collect_count) + "人看过"
        holder?.actor?.text = "主演：" + getActorString(data[position].casts!!)
        holder?.itemView?.setOnClickListener {
            if (itemClick != null) {
                itemClick.invoke(it, position, holder?.image!!)
            }

        }
        holder?.btn?.setOnClickListener {
            if (itemBtnClick != null)
                itemBtnClick.invoke(it, position)
        }
    }

    fun setOnItemClickListener(listener: (itemView: View, position: Int, shareView: View) -> Unit) {
        itemClick = listener
    }

    fun setOnItemBtnClickListener(listener: (view: View, position: Int) -> Unit) {
        itemBtnClick = listener
    }


    private fun getActorString(acList: List<CastsBean>): String {
        val builder = StringBuilder()
        acList.iterator().forEach {
            builder.append(it.name + " / ")
        }
        builder.delete(builder.length - 2, builder.length)
        return builder.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView? = null
        var dy: TextView? = null
        var actor: TextView? = null
        var watchNum: TextView? = null
        var btn: Button? = null
        var rating: RatingBar? = null
        var image: ImageView? = null

        init {
            title = itemView.findViewById(R.id.item_hot_screen_title) as TextView?
            dy = itemView.findViewById(R.id.item_hot_screen_dy) as TextView?
            actor = itemView.findViewById(R.id.item_hot_screen_actor) as TextView?
            watchNum = itemView.findViewById(R.id.item_hot_screen_watch_num) as TextView?
            btn = itemView.findViewById(R.id.item_hot_screen_btn) as Button?
            rating = itemView.findViewById(R.id.item_hot_screen_rating) as RatingBar?
            image = itemView.findViewById(R.id.item_hot_screen_image) as ImageView?
        }
    }


}