package com.proyek.c19tracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proyek.c19tracker.Model.ProvinsiResponse
import com.proyek.c19tracker.R
import kotlinx.android.synthetic.main.layout_kasus_provinsi.view.*


class ProvinsiAdapter(private var list: ArrayList<ProvinsiResponse>):
    RecyclerView.Adapter<ProvinsiAdapter.ProvinsiViewHolder>() {

    inner class ProvinsiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(provinsi: ProvinsiResponse) {
            with(itemView) {
                provinsi_list.text = provinsi.attributes.provinsi
                positif_list.text = provinsi.attributes.positif.toString()
                sembuh_list.text = provinsi.attributes.sembuh.toString()
                meninggal_list.text = provinsi.attributes.meninggal.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinsiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_kasus_provinsi, parent, false)
        return ProvinsiViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProvinsiViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}