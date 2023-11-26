package com.example.neteasecloudmusic.bean

import java.io.Serializable

data class UserInfo (
    var pk_id: Int = 0,
    var name: String = "",
    var phone: String = "",
    var level: Int = 0,
    var introduction: String = "",
    var create_time: String = "",
    var modified_time: String = ""
): Serializable