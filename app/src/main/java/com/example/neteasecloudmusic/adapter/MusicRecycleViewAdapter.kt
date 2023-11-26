package com.example.neteasecloudmusic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.neteasecloudmusic.R
import com.example.neteasecloudmusic.adapter.impl.MusicRecycleViewAdapterOnClickListener

import android.widget.TextView
import com.example.neteasecloudmusic.bean.MusicBean

class MusicRecycleViewAdapter(): RecyclerView.Adapter<MusicRecycleViewAdapter.ViewHolder>() {
    private lateinit var context: Context
    private lateinit var musicList: ArrayList<MusicBean>
    private var musicOnClickListener: MusicRecycleViewAdapterOnClickListener ?= null

    constructor(context: Context, musicList: ArrayList<MusicBean>): this() {
        this.context = context
        this.musicList = musicList
    }

    fun setOnClick(musicOnClickListener: MusicRecycleViewAdapterOnClickListener) {
        this.musicOnClickListener = musicOnClickListener
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val linearLayout: LinearLayout = view.findViewById(R.id.linearLayout)
        val name:TextView = view.findViewById(R.id.name)
        val singer:TextView = view.findViewById(R.id.singer)
        val introduction:TextView = view.findViewById(R.id.introduction)
    }

    fun resetData(musicList: ArrayList<MusicBean>) {
        this.musicList = musicList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.music_elem_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = musicList[position]
        musicOnClickListener?.linearLayoutOnClickListener(holder.linearLayout, position)
        holder.name.text = info.songName
        holder.singer.text = info.singer
        holder.introduction.text = info.introduction
    }

    override fun getItemCount(): Int {
        return musicList.size
    }
}