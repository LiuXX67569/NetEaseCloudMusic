package com.example.neteasecloudmusic.utils

object ApplicationProperties {
    private const val internet: String = "10.200.227.193"
    private const val port: String = "8081"
    private const val servlet: String = "http://${internet}:${port}/musicAppServer_war_exploded/"
    const val musicServlet: String = "${servlet}MusicServlet"
    const val songListServlet: String = "${servlet}SongListServlet"
    const val userInfoServlet: String = "${servlet}UserInfoServlet"
}