package com.example.neteasecloudmusic.fragment

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.neteasecloudmusic.R
import com.example.neteasecloudmusic.adapter.MusicRecycleViewAdapter
import com.example.neteasecloudmusic.bean.MusicBean

class DiscoverFragment : Fragment() {
    private lateinit var context: Context
    private lateinit var recyclerView: RecyclerView
    private lateinit var musicRecycleViewAdapter: MusicRecycleViewAdapter
    private var musicList: ArrayList<MusicBean> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.discover_layout, container, false)
        context = view.context
        //init animation
        val imgViAnima: ImageView = view.findViewById(R.id.discover_imgViAnima)
        val animationDrawable = imgViAnima.drawable as AnimationDrawable
        animationDrawable.start()
//        recyclerView = view.findViewById(R.id.discoverRecycleView)
//        initData()
//
//        val layoutManager = LinearLayoutManager(context)
//        layoutManager.orientation = LinearLayoutManager.VERTICAL
//        recyclerView.layoutManager = layoutManager
//
//        musicRecycleViewAdapter = MusicRecycleViewAdapter(context, musicList)
//        recyclerView.adapter = musicRecycleViewAdapter
        return view
    }

    private fun initData() {
        for (i in 0 until 10) {
            musicList.add(MusicBean())
        }
    }
}