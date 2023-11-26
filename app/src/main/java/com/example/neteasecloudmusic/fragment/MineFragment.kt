package com.example.neteasecloudmusic.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.neteasecloudmusic.activity.EditUserInfoActivity
import com.example.neteasecloudmusic.activity.StorageBoxActivity
import com.example.neteasecloudmusic.R

class MineFragment : Fragment() {
    private lateinit var context: Context
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.mine_layout, container, false)
        context = view.context
        val imgTurnToEditPersonalData: ImageView = view.findViewById(R.id.mine_imgViEditPersonalData)
        val rlTurnToEditPersonalData: RelativeLayout = view.findViewById(R.id.mine_rlEditPersonalData)
        val tvFollowing: TextView = view.findViewById(R.id.mine_tvFollowing)
        val tvFollower: TextView = view.findViewById(R.id.mine_tvFollower)
        val tvLevel: TextView = view.findViewById(R.id.mine_tvLevel)
        val imgBtnRecentPlay: ImageButton = view.findViewById(R.id.mine_imgBtnRecentPlay)
        val imgBtnDownload: ImageButton = view.findViewById(R.id.mine_imgBtnDownload)
        val imgBtnFriends: ImageButton = view.findViewById(R.id.mine_imgBtnFriends)
        val btnCollectList: ImageButton = view.findViewById(R.id.mine_imgBtnCollectList)
        val trTurnToLikeList: TableRow = view.findViewById(R.id.mine_trLikeList)

        imgTurnToEditPersonalData.setOnClickListener {
            val intent = Intent(context, EditUserInfoActivity::class.java)
            startActivity(intent)
        }

        rlTurnToEditPersonalData.setOnClickListener {
            Toast.makeText(context, "跳转到个人资料编辑页面", Toast.LENGTH_SHORT).show()
        }

        tvFollowing.setOnClickListener {
            Toast.makeText(context, "跳转到关注页面", Toast.LENGTH_SHORT).show()
        }

        tvFollower.setOnClickListener {
            Toast.makeText(context, "跳转到粉丝页面", Toast.LENGTH_SHORT).show()
        }

        imgBtnRecentPlay.setOnClickListener {
            Toast.makeText(context, "跳转到最近播放页面", Toast.LENGTH_SHORT).show()
        }

        imgBtnDownload.setOnClickListener {
            Toast.makeText(context, "跳转，读取下载的音乐，并列出", Toast.LENGTH_SHORT).show()
        }

        imgBtnFriends.setOnClickListener {
            Toast.makeText(context, "跳转到好友页面", Toast.LENGTH_SHORT).show()
        }

        btnCollectList.setOnClickListener {
            val intent = Intent(context, StorageBoxActivity::class.java)
            startActivity(intent)
        }

        trTurnToLikeList.setOnClickListener {
            Toast.makeText(context, "跳转到被点赞过的歌曲的页面", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}