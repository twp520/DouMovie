package com.colin.doumovie.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.colin.doumovie.R
import com.colin.doumovie.bean.CommentBean

/**
 * Created by tianweiping on 2017/12/21.
 */
class MovieCommentListAdapter(val context: Context, val data: List<CommentBean.CommentListBean>) : RecyclerView.Adapter<MovieCommentListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.name?.text = data[position].author?.name
        Glide.with(context)
                .load(data[position].author?.avatar)
                .into(holder?.icon)
        holder?.content?.text = data[position].content
        holder?.rating?.rating = data[position].rating?.value?.toFloat()!!
        holder?.itemView?.setOnClickListener {
            //TODO 影评信息
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_movie_detail_comment_layout, parent, false))
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon: ImageView? = null
        var name: TextView? = null
        var content: TextView? = null
        var rating: RatingBar? = null

        init {
            icon = itemView.findViewById(R.id.item_movie_detail_comment_icon) as ImageView?
            name = itemView.findViewById(R.id.item_movie_detail_comment_name) as TextView?
            content = itemView.findViewById(R.id.item_movie_detail_comment_content) as TextView?
            rating = itemView.findViewById(R.id.item_movie_detail_comment_rating) as RatingBar?

        }
    }
}