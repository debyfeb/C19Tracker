package com.proyek.c19tracker.ui.tips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyek.c19tracker.Model.Item
import com.proyek.c19tracker.R
import com.proyek.c19tracker.adapter.TipsAdapter


class TipsFragment : Fragment() {
    lateinit var recycler: RecyclerView
    private val tipList = ArrayList<Item>()
    private val adapter: TipsAdapter = TipsAdapter(tipList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_tips, container, false)
        recycler = root.findViewById(R.id.recyclerView)
        val adapterr = TipsAdapter(tipList)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = adapterr
        return root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tipList.add(Item(R.drawable.wash_your_hands, "Hand Wash","Jangan lupa untuk mencuci tangan setiap 1 menit. Agar tangan anda tetap bersih apabila habis menyentuh sesuatu"))
        tipList.add(Item(R.drawable.mask, "Wear Mask","Jangan lupa pakai masker agar udara yang terkena virus tidak sampai terhirup"))
        tipList.add(Item(R.drawable.social_distancing, "Social Distance","Jaga jarak anda dengan orang lain setidaknya 2 meter. "))
        tipList.add(Item(R.drawable.spray, "Clean Object & Surface","Jangan Lupa untuk membersihkan benda ataupun tempat anda agar terhindar dari virus yang masih menempel."))
        adapter.notifyDataSetChanged()
    }

}
