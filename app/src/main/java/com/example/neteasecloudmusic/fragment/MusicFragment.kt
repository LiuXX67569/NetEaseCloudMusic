package com.example.neteasecloudmusic.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neteasecloudmusic.R
import com.example.neteasecloudmusic.adapter.MusicRecycleViewAdapter
import com.example.neteasecloudmusic.adapter.impl.MusicRecycleViewAdapterOnClickListener
import com.example.neteasecloudmusic.bean.MusicBean

class MusicFragment : Fragment(){
    private lateinit var context: Context
    private lateinit var recyclerView: RecyclerView
    private lateinit var musicRecycleViewAdapter: MusicRecycleViewAdapter
    private var musicList: ArrayList<MusicBean> = ArrayList()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.music_layout, container, false)
        recyclerView = view.findViewById(R.id.music_recycleView)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        musicRecycleViewAdapter = MusicRecycleViewAdapter(context, musicList)
        recyclerView.adapter = musicRecycleViewAdapter
        musicRecycleViewAdapter.setOnClick(object: MusicRecycleViewAdapterOnClickListener {
            override fun linearLayoutOnClickListener(view: View, position: Int) {
                view.setOnClickListener {
                    Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
        return view
    }

    fun resetData(){
        if (!::musicRecycleViewAdapter.isInitialized) {
            musicRecycleViewAdapter = MusicRecycleViewAdapter()
        }
        musicRecycleViewAdapter.resetData(musicList)
    }

    fun setData(musicList: ArrayList<MusicBean>) {
        this.musicList = musicList
    }
}