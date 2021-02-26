package com.proyek.c19tracker.ui.lokasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyek.c19tracker.Api.RetrofitClient
import com.proyek.c19tracker.Model.IndonesiaResponse
import com.proyek.c19tracker.Model.ProvinsiResponse
import com.proyek.c19tracker.R
import com.proyek.c19tracker.adapter.ProvinsiAdapter
import kotlinx.android.synthetic.main.fragment_lokasi.*
import kotlinx.android.synthetic.main.fragment_lokasi.txtMeninggal
import kotlinx.android.synthetic.main.fragment_lokasi.txtPositif
import kotlinx.android.synthetic.main.fragment_lokasi.txtSembuh
import kotlinx.android.synthetic.main.fragment_tips.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LokasiFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_lokasi, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply{
            showIndonesia()
            showProvinsi()
        }
    }

    private fun showIndonesia(){
        RetrofitClient.instance.getIndonesia().enqueue(object:
            Callback<ArrayList<IndonesiaResponse>> {
            override fun onFailure(call: Call<ArrayList<IndonesiaResponse>>, t: Throwable) {
                Toast.makeText(activity,"${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ArrayList<IndonesiaResponse>>,
                response: Response<ArrayList<IndonesiaResponse>>
            ) {
                val indonesia = response.body()?.get(0)
                val positif = indonesia?.positif
                val sembuh = indonesia?.sembuh
                val meninggal = indonesia?.meninggal

                txtPositif.text = positif
                txtSembuh.text = sembuh
                txtMeninggal.text = meninggal
            }
        })
    }

    private fun showProvinsi(){
        provinsi_view.setHasFixedSize(true)
        provinsi_view.layoutManager = LinearLayoutManager(activity)
        RetrofitClient.instance.getProvinsi().enqueue(object :
            Callback<ArrayList<ProvinsiResponse>> {
            override fun onResponse(
                call: Call<ArrayList<ProvinsiResponse>>,
                response: Response<ArrayList<ProvinsiResponse>>
            ) {
                val list = response.body()
                val adapter = list?.let { ProvinsiAdapter(it) }
                provinsi_view.adapter = adapter
            }
            override fun onFailure(call: Call<ArrayList<ProvinsiResponse>>, t: Throwable) {
                Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


}