package com.example.neteasecloudmusic.utils.impl

import org.json.JSONArray

interface CallBack {
    fun onFinish(jsonArray: JSONArray)
    fun onError(e: Exception)
}