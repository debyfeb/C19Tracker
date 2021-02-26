package com.proyek.c19tracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proyek.c19tracker.Model.Item
import com.proyek.c19tracker.R
import kotlinx.android.synthetic.main.row.view.*

class TipsAdapter(val tipList: ArrayList<Item>) : RecyclerView.Adapter<TipsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item: Item){
            itemView.imageView.setImageResource(item.imageview)
            itemView.titleTips.text = item.titleText
            itemView.textDetail.text = item.textDetail
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row,parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(tipList[position])
    }

    override fun getItemCount(): Int {
        return tipList.size
    }
}