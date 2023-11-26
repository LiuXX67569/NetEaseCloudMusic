package com.example.neteasecloudmusic

data class SongInfo (
    val id:Int,             //id
    val name: String,       //歌曲名称
    val singer: String,     //歌手名称
    val album: String,      //专辑名称
    val songId: Int,        //歌曲id
    val singerId: Int,      //歌手id
    val albumId: Int        //专辑id
)