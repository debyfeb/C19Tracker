package com.proyek.c19tracker.Api

import com.proyek.c19tracker.Model.IndonesiaResponse
import com.proyek.c19tracker.Model.ProvinsiResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("indonesia")
    fun getIndonesia(): Call<ArrayList<IndonesiaResponse>>

    @GET("indonesia/provinsi")
    fun getProvinsi(): Call<ArrayList<ProvinsiResponse>>
}