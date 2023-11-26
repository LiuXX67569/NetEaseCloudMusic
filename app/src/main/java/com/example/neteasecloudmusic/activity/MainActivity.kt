package com.example.neteasecloudmusic.activity

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.neteasecloudmusic.R
import com.example.neteasecloudmusic.activityresultcontract.ParamsLoginActivityResultContract
import com.example.neteasecloudmusic.adapter.ViewPaperAdapter
import com.example.neteasecloudmusic.bean.UserInfo
import com.example.neteasecloudmusic.databinding.MainLayoutBinding
import com.example.neteasecloudmusic.fragment.CustomBottomDialog
import com.example.neteasecloudmusic.fragment.DiscoverFragment
import com.example.neteasecloudmusic.fragment.MineFragment
import com.example.neteasecloudmusic.ui.theme.NetEaseCloudMusicTheme
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainLayoutBinding
    private lateinit var drawerLayout: DrawerLayout
    private var fragments: ArrayList<Fragment> = ArrayList()
    private val tabTitle = listOf("发现", "我的")
    private val tabIcon = listOf(R.drawable.img_disc, R.drawable.img_me)
    private var userInfo = UserInfo()
    private val paramsLoginActivityLauncher = registerForActivityResult(
        ParamsLoginActivityResultContract()
    ) {
        result ->
        userInfo = result
    }
    private lateinit var mediaPlayer: MediaPlayer//音乐播放器
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paramsLoginActivityLauncher.launch(UserInfo())
        binding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //init toolbar
        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.img_extra_cross)
        }
        //init drawerLayout
        drawerLayout = binding.mainDrawerLayout
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        binding.mainNavView.let { it ->
            it.setCheckedItem(R.id.nav_about)
            it.setNavigationItemSelectedListener {
                drawerLayout.closeDrawers()
                when (it.itemId) {
                    R.id.nav_about -> {
                        Toast.makeText(this, "关于我们", Toast.LENGTH_SHORT).show()
                    }
                    R.id.nav_exit -> {
                        Snackbar.make(drawerLayout, "继续退出", Snackbar.LENGTH_SHORT)
                            .setAction("Do it!") {
                                Toast.makeText(this, "滚！", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            .setAction("Undo") {
                                Toast.makeText(this, "取消退出", Toast.LENGTH_SHORT)
                                    .show()
                            }.show()
                    }
                    R.id.header_imgViPortrait -> {
                        Toast.makeText(this, "我的界面", Toast.LENGTH_SHORT).show()
                    }
                    R.id.header_tvUsername -> {
                        Toast.makeText(this, "我的界面", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
        }
        //init fragments
        val discoverFragment = DiscoverFragment()
        val mineFragment = MineFragment()
        fragments.add(discoverFragment)
        fragments.add(mineFragment)
        //init viewPaper
        val viewPaper = binding.mainViewPager
        val viewPaperAdapter = ViewPaperAdapter(this, fragments)
        viewPaper.adapter = viewPaperAdapter
        viewPaper.currentItem = 0
        //init tabLayout
        val tabLayout = binding.mainTabLayout
        TabLayoutMediator(tabLayout, viewPaper
        ) { tab, position ->
            tab.text = tabTitle[position]
            tab.setIcon(tabIcon[position])
        }.attach()
        //main
        binding.mainTvSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            val searchText = binding.searchText.text.toString()
            binding.searchText.text.clear()
            intent.putExtra("searchText", searchText)
            startActivity(intent)
        }

//        binding.mainImgBtnPopWindow.setOnClickListener {
//            val customBottomDialog = CustomBottomDialog()
//            customBottomDialog.show(supportFragmentManager, "1")
//        }
        // 初始化MediaPlayer，不能放在按钮控件中，否则无法暂停
        val mediaPlayer = MediaPlayer.create(this, R.raw.testmusic)
        //点击Home界面播放按钮
        binding.IBplayerplay.setOnClickListener {
            if (mediaPlayer.isPlaying)//暂停
            {
                mediaPlayer.pause()
                binding.IBplayerplay.setImageResource(R.drawable.img_play)
            }
            else//播放
            {
                mediaPlayer.start()
                binding.IBplayerplay.setImageResource(R.drawable.img_stop)
            }
        }
        binding.TRmusicplayer.setOnClickListener {
            val intent = Intent(this, MusicplayerActivity::class.java)//打开MusicplayerActivity
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return true
    }
    override fun onDestroy() {//释放 MediaPlayer
        super.onDestroy()
        mediaPlayer.release()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NetEaseCloudMusicTheme {
        Greeting("Android")
    }
}