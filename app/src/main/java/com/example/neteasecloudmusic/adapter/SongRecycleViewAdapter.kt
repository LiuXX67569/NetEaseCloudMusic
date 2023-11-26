package com.example.neteasecloudmusic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.neteasecloudmusic.R
import com.example.neteasecloudmusic.SongInfo

class SongRecycleViewAdapter() : RecyclerView.Adapter<SongRecycleViewAdapter.ViewHolder> () {
    private lateinit var context: Context
    private var songInfoList: ArrayList<SongInfo> = ArrayList()

    constructor(context: Context, songInfoList: ArrayList<SongInfo>) : this() {
        this.context = context
        this.songInfoList = songInfoList
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvIndex: TextView = view.findViewById(R.id.tvIndex)
        val tvSong: TextView = view.findViewById(R.id.tvSong_name)
        val tvSinger:TextView = view.findViewById(R.id.tvSinger_name)
        val tvAlbum: TextView = view.findViewById(R.id.tvAlbum_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.song_elem_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return songInfoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val songInfo = songInfoList[position]
        val index = position + 1
        holder.tvIndex.text = "$index"
        holder.tvSong.text = songInfo.name
        holder.tvSinger.text = songInfo.singer
        holder.tvAlbum.text = songInfo.album
    }
}