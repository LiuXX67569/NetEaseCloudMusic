package com.example.neteasecloudmusic.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neteasecloudmusic.SongInfo
import com.example.neteasecloudmusic.SongListInfo
import com.example.neteasecloudmusic.adapter.SongRecycleViewAdapter
import com.example.neteasecloudmusic.databinding.ActivitySongListBinding

class SongListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySongListBinding
    private val songList = ArrayList<SongInfo>()
    private val songListInfo: SongListInfo = SongListInfo(1,"","","");
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        binding.tvUserName.text = songListInfo.user
        binding.tvSonglistName.text = songListInfo.name
        binding.tvintroduction.text = songListInfo.introduction

        val layoutManager = LinearLayoutManager(this)
        val trainRecyclerView = binding.songRecyclerView
        trainRecyclerView.layoutManager = layoutManager
        trainRecyclerView.addItemDecoration(
            DividerItemDecoration(
                trainRecyclerView.context,
                layoutManager.orientation
            )
        )
        val adapter = SongRecycleViewAdapter(this, songList)//  设置适配器
        trainRecyclerView.adapter = adapter

    }
    private fun init(){
        songList.add(SongInfo(123,"逆战", "张杰", "逆战", 1, 2, 3))
        songList.add(SongInfo(123,"星星", "张杰", "星星", 1, 2, 3))
        songListInfo.songListId = 1;
        songListInfo.user = "shympo"
        songListInfo.name = "一号歌单"
        songListInfo.introduction = "测试"
    }
}