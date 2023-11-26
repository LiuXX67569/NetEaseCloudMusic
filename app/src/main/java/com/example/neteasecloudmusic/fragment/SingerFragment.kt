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
import com.example.neteasecloudmusic.adapter.SingerRecycleViewAdapter
import com.example.neteasecloudmusic.bean.SingerBean

class SingerFragment: Fragment() {
    private lateinit var context: Context
    private lateinit var recyclerView: RecyclerView
    private lateinit var singerRecycleViewAdapter: SingerRecycleViewAdapter
    private var singerList: ArrayList<SingerBean> = ArrayList()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.singer_layout, container, false)
        recyclerView = view.findViewById(R.id.singer_recycleView)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        singerRecycleViewAdapter = SingerRecycleViewAdapter(context, singerList)
        recyclerView.adapter = singerRecycleViewAdapter
        return view
    }

    fun resetData(){
        if (!::singerRecycleViewAdapter.isInitialized) {
            singerRecycleViewAdapter = SingerRecycleViewAdapter()
        }
        singerRecycleViewAdapter.resetData(singerList)
    }

    fun setData(singerList: ArrayList<SingerBean>) {
        this.singerList = singerList
//        singerRecycleViewAdapter.resetData(singerList)
    }
}