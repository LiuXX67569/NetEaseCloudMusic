package com.example.neteasecloudmusic.bean

import java.io.Serializable

data class SingerBean (
    var singer_id:Int = 0,
    var name: String = "test name",
    var photo_path: String = "test path"
): Serializable