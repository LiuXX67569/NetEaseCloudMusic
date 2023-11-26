package com.example.neteasecloudmusic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.neteasecloudmusic.R
import com.example.neteasecloudmusic.bean.SingerBean

class SingerRecycleViewAdapter(): RecyclerView.Adapter<SingerRecycleViewAdapter.ViewHolder>() {
    private lateinit var context: Context
    private lateinit var singerList: ArrayList<SingerBean>
    constructor(context: Context, singerList: ArrayList<SingerBean>): this() {
        this.context = context
        this.singerList = singerList
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
    }

    fun resetData(singerList: ArrayList<SingerBean>) {
        this.singerList = singerList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.singer_elem_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return singerList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = singerList[position]
        holder.name.text = info.name
    }
}