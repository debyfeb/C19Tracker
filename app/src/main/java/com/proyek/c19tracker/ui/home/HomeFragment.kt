package com.proyek.c19tracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.proyek.c19tracker.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getGlobalData()
    }



    fun getGlobalData(){
        val url:String ="https://corona.lmao.ninja/v2/all/"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener<String> {
                var jsonObject = JSONObject(it.toString())
                textPositif.text = jsonObject.getString("cases")
                textSembuh.text = jsonObject.getString("recovered")
                textMeninggal.text = jsonObject.getString("deaths")
            },
            Response.ErrorListener {
                Toast.makeText(activity,it.toString(), Toast.LENGTH_LONG).show()
                textPositif.text = "-"
                textSembuh.text = "-"
                textMeninggal.text = "-"
            }
        )

        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(stringRequest)
    }



}
