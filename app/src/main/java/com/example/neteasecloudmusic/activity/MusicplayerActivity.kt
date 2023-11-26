package com.example.neteasecloudmusic.activity

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import com.bumptech.glide.Glide
import com.example.neteasecloudmusic.R
import com.example.neteasecloudmusic.activity.RotationView
import com.example.neteasecloudmusic.api.ApiClient
import com.example.neteasecloudmusic.api.MusicApi
import com.example.neteasecloudmusic.databinding.MusicplayerActivityLayoutBinding
import com.example.neteasecloudmusic.bean.MusicBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MusicplayerActivity : AppCompatActivity() {
    private lateinit var binding: MusicplayerActivityLayoutBinding
    private lateinit var rotationView: RotationView
    private lateinit var mediaPlayer: MediaPlayer
    private var isPlaying = false

    //private val musicList = arrayOf(R.raw.testmusic, R.raw.test2music, R.raw.test3music)
//    private val coverImageList = arrayOf(
//        R.drawable.testimg,
//        R.drawable.test2img,
//        R.drawable.test3img
//    )
    private var currentMusicIndex = 0

    private lateinit var seekBar: SeekBar//进度条

//    private val songList = arrayOf(//歌曲信息数组
//        MusicBean(),
//        MusicBean(),
//        MusicBean()
//    )
    private var songList = arrayOf<MusicBean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = MusicplayerActivityLayoutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        rotationView = findViewById(R.id.rotationView)

//        mediaPlayer = MediaPlayer.create(this, musicList[currentMusicIndex])
//        rotationView.setImageResource(coverImageList[currentMusicIndex])
        // 使用当前歌曲信息初始化 MediaPlayer 和界面元素
        val currentSong = songList[currentMusicIndex]
        mediaPlayer = MediaPlayer.create(this, Uri.parse(currentSong.filePath))

        val coverImagePath = currentSong.imagePath
        if (coverImagePath != null && coverImagePath.isNotEmpty()) {
            // 使用 Glide 加载图片
            Glide.with(this)
                .load(coverImagePath) // 这里是你的服务器图片路径
                .into(rotationView)
        }

        seekBar = findViewById(R.id.seekBar)

        updateSongDetails()//初始化歌曲信息

        binding.IBplay.setOnClickListener {
            if (isPlaying) {
                mediaPlayer.pause()
                rotationView.pauseRotation()
                isPlaying = false
                binding.IBplay.setImageResource(R.drawable.img_playerplay)
            } else {
                mediaPlayer.start()
                rotationView.startRotation()
                isPlaying = true
                binding.IBplay.setImageResource(R.drawable.img_playerstop)
            }
        }

        binding.IBnext.setOnClickListener {
            changeMusic(1)
        }

        binding.IBlast.setOnClickListener {
            changeMusic(-1)
        }
        binding.IBreturn.setOnClickListener {
            onBackPressed()
        }

        // 设置SeekBar监听器,进度条
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 用户拖动SeekBar时触发
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // 用户开始拖动SeekBar时触发
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // 用户停止拖动SeekBar时触发
            }
        })

        // 在MediaPlayer的prepared监听器中设置SeekBar的最大值
        mediaPlayer.setOnPreparedListener {
            seekBar.max = mediaPlayer.duration
        }

        // 更新SeekBar的进度
        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                if (!isFinishing) {
                    seekBar.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000) // 每秒更新一次
                }
            }
        })

        // 添加网络请求代码
        // 创建 Retrofit 实例
        val retrofit = ApiClient.retrofit
        val musicApi = ApiClient.retrofit.create(MusicApi::class.java)
        val call = musicApi.getAllMusic()

        call.enqueue(object : Callback<List<MusicBean>> {
            override fun onResponse(call: Call<List<MusicBean>>, response: Response<List<MusicBean>>) {
                // 在网络请求成功的回调中更新 songList
                if (response.isSuccessful) {
                    val musicList = response.body()
                    if (musicList != null) {
                        // 将服务器返回的音乐列表赋值给 songList
                        songList = musicList.toTypedArray()
                        // 更新当前音乐
                        changeMusic(0)
                    }
                } else {
                    // 处理服务器返回的错误
                    // 根据错误码或其他信息进行相应处理
                }
            }

            override fun onFailure(call: Call<List<MusicBean>>, t: Throwable) {
                // 处理网络请求失败的情况
                // 可以在这里添加适当的错误处理逻辑
            }
        })


    }


    private fun changeMusic(offset: Int) {
        currentMusicIndex = (currentMusicIndex + offset + songList.size) % songList.size
        mediaPlayer.release()
        mediaPlayer = MediaPlayer.create(this, Uri.parse(songList[currentMusicIndex].filePath))

        if (isPlaying) {
            mediaPlayer.start()
            rotationView.startRotation()
            binding.IBplay.setImageResource(R.drawable.img_playerstop)
        } else {
            rotationView.pauseRotation()
            binding.IBplay.setImageResource(R.drawable.img_playerplay)
        }

        //rotationView.setImageResource(coverImageList[currentMusicIndex])

        // Update song details after changing music
        updateSongDetails()//更新歌曲信息
    }

    private fun updateSongDetails() {//更新歌曲信息
        val currentSong = songList[currentMusicIndex]
        binding.tvSongTitle.text = currentSong.songName
        binding.tvArtist.text = currentSong.singer

        val coverImagePath = currentSong.imagePath
        if (coverImagePath != null && coverImagePath.isNotEmpty()) {
            // 使用 Glide 加载图片
            Glide.with(this)
                .load(coverImagePath) // 这里是你的服务器图片路径
                .into(rotationView)
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer != null) {
            mediaPlayer.release()
        }
    }

    override fun onBackPressed() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        super.onBackPressed()
    }

}
