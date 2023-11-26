package com.example.neteasecloudmusic.utils

import android.util.ArrayMap
import com.example.neteasecloudmusic.bean.MusicBean
import com.example.neteasecloudmusic.bean.SingerBean
import com.example.neteasecloudmusic.utils.impl.CallBack
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import java.net.URLEncoder
import kotlin.concurrent.thread

object HttpConnUtil {
    fun parseJSONtoMusicBean(jsonArray: JSONArray): ArrayList<MusicBean> {
        val resultList:ArrayList<MusicBean> = ArrayList()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            resultList.add(MusicBean(
                jsonObject.getInt("id"),
                jsonObject.getString("song_name"),
                jsonObject.getString("singer"),
                jsonObject.getString("mp3_file_path"),
                jsonObject.getString("cover_image_path"),
                jsonObject.getString("lyrics_path"),
                jsonObject.getString("introduction")
            ))
        }
        return resultList
    }

    fun parseJSONtoSingerBean(jsonArray: JSONArray): ArrayList<SingerBean> {
        val resultList:ArrayList<SingerBean> = ArrayList()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            resultList.add(
                SingerBean(
                jsonObject.getInt("id"),
                jsonObject.getString("singer_name"),
                jsonObject.getString("photo_path")
            )
            )
        }
        return resultList
    }

    fun sendRequestWithOkHttpClient(servlet: String, arrayMap: ArrayMap<String, String>, callBack: CallBack) {
        thread {
            try {
                val client = OkHttpClient()
                val requestBody = FormBody.Builder()
                for ((key, value ) in arrayMap) {
                    requestBody.add(key, URLEncoder.encode(value, "UTF-8"))
                }
                val request = Request.Builder()
                    .url(servlet)
                    .post(requestBody.build())
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                var result = JSONArray()
                if (responseData != null) {
                    result = JSONArray(responseData)
                }
                callBack.onFinish(result)
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }
}