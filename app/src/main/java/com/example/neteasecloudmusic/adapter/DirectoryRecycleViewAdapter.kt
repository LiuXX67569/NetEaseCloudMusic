package com.example.neteasecloudmusic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neteasecloudmusic.R
import com.example.neteasecloudmusic.bean.DirectoryBean

class DirectoryRecycleViewAdapter(): RecyclerView.Adapter<DirectoryRecycleViewAdapter.ViewHolder>() {
    private lateinit var context: Context
    private var directoryList: ArrayList<DirectoryBean> = ArrayList()

    constructor(context: Context, directoryList: ArrayList<DirectoryBean>): this() {
        this.context = context
        this.directoryList = directoryList
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    fun resetData(directoryList: ArrayList<DirectoryBean>) {
        this.directoryList = directoryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.directory_elem_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return directoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = directoryList[position]
    }
}