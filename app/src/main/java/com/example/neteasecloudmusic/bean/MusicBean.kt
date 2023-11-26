package com.example.neteasecloudmusic.bean

import java.io.Serializable

data class MusicBean (
    val id: Int = 0,
    val songName: String = "tempName",
    val singer: String = "tempSinger",
    val filePath: String = "",
    val imagePath: String = "",
    val lyricsPath: String = "",
    val introduction: String = "introduction"
): Serializable

//private val songList = arrayOf(
//    MusicBean("Song 1", "Artist 1"),
//    MusicBean("Song 2", "Artist 2"),
//    MusicBean("Song 3", "Artist 3")
//)


