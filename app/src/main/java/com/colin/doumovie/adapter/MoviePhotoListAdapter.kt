package com.colin.doumovie.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.colin.doumovie.R
import com.colin.doumovie.bean.MovieDetailResult

/**
 * Created by tianweiping on 2017/12/21.
 */
class MoviePhotoListAdapter(val context: Context, val data: List<MovieDetailResult.PhotosBean>)
    : RecyclerView.Adapter<MoviePhotoListAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_movie_detail_photos_layout, null, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        Glide.with(context)
                .load(data[position].image)
                .into(holder?.image)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView? = null

        init {
            image = itemView.findViewById(R.id.item_movie_detail_photos_image) as ImageView?

        }
    }
}