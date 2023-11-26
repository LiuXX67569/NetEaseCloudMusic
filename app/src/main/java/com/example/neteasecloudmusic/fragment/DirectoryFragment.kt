package com.example.neteasecloudmusic.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neteasecloudmusic.R
import com.example.neteasecloudmusic.adapter.DirectoryRecycleViewAdapter
import com.example.neteasecloudmusic.bean.DirectoryBean

class DirectoryFragment: Fragment() {
    private lateinit var context: Context
    private lateinit var recyclerView: RecyclerView
    private lateinit var directoryRecycleViewAdapter: DirectoryRecycleViewAdapter
    private val directoryList: ArrayList<DirectoryBean> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.directory_layout, container, false)
        context = view.context
        recyclerView = view.findViewById(R.id.dir_recycleView)
        initData()

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        directoryRecycleViewAdapter = DirectoryRecycleViewAdapter(context, directoryList)
        recyclerView.adapter = directoryRecycleViewAdapter
        return view
    }

    private fun initData() {
        for (i in 0 until 10) {
            directoryList.add(DirectoryBean())
        }
    }

    fun setData(directoryList: ArrayList<DirectoryBean>) {
        directoryRecycleViewAdapter.resetData(directoryList)
    }
}