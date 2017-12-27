package com.colin.doumovie.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.colin.doumovie.R
import com.colin.doumovie.bean.CastsBean

/**
 * Created by tianweiping on 2017/12/21.
 */
class ActorListAdapter(val context: Context, val data: MutableList<CastsBean>) : RecyclerView.Adapter<ActorListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.name?.text = data[position].name
        Glide.with(context)
                .load(data[position].avatars?.medium)
                .into(holder?.image)
        holder?.type?.text = if (data[position].type == 0) {
            "演员"
        } else {
            "导演"
        }
        holder?.itemView?.setOnClickListener {
            //TODO 影人信息
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_movie_detail_actor_layout, parent, false))
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView? = null
        var name: TextView? = null
        var type: TextView? = null

        init {
            image = itemView.findViewById(R.id.item_movie_detail_actor_image) as ImageView?
            name = itemView.findViewById(R.id.item_movie_detail_actor_name) as TextView?
            type = itemView.findViewById(R.id.item_movie_detail_actor_type) as TextView?
        }
    }
}