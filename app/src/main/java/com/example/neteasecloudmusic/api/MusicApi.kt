package com.example.neteasecloudmusic.api

import com.example.neteasecloudmusic.bean.MusicBean
import retrofit2.Call
import retrofit2.http.GET

interface MusicApi {
    @GET("MusicServlet?action=findAll")
    fun getAllMusic(): Call<List<MusicBean>>
}