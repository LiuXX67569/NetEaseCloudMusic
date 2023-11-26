package com.example.neteasecloudmusic.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.ArrayMap
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.neteasecloudmusic.adapter.ViewPaperAdapter
import com.example.neteasecloudmusic.databinding.SearchLayoutBinding
import com.example.neteasecloudmusic.fragment.DirectoryFragment
import com.example.neteasecloudmusic.fragment.MusicFragment
import com.example.neteasecloudmusic.fragment.SearchAllFragment
import com.example.neteasecloudmusic.fragment.SingerFragment
import com.example.neteasecloudmusic.utils.ApplicationProperties
import com.example.neteasecloudmusic.utils.HttpConnUtil
import com.example.neteasecloudmusic.utils.impl.CallBack
import com.google.android.material.tabs.TabLayoutMediator
import org.json.JSONArray

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: SearchLayoutBinding
    private var fragments: ArrayList<Fragment> = ArrayList()
    private val tabTitle = listOf("综合", "单曲", "歌单", "歌手")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //init toolbar
        setSupportActionBar(binding.searchToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //init fragments
        val searchAllFragment = SearchAllFragment()
        val musicFragment = MusicFragment()
        val directoryFragment = DirectoryFragment()
        val singerFragment = SingerFragment()
        fragments.add(searchAllFragment)
        fragments.add(musicFragment)
        fragments.add(directoryFragment)
        fragments.add(singerFragment)
        //init viewPaper
        val viewPaper = binding.searchViewPager
        val viewPaperAdapter = ViewPaperAdapter(this, fragments)
        viewPaper.adapter = viewPaperAdapter
        viewPaper.currentItem = 0
        //init tabLayout
        val tabLayout = binding.searchTabLayout
        tabLayout.setSelectedTabIndicatorColor(Color.RED)
        TabLayoutMediator(tabLayout, viewPaper
        ) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
        //搜索
        var searchText = intent.getStringExtra("searchText")
        binding.searchText.setText(searchText)
//        val searchText = binding.searchText.text.toString()
        val musicMap: ArrayMap<String, String> = ArrayMap()
        musicMap["name"] = searchText
        //查询歌曲
        musicMap["action"] = "findByName"
        HttpConnUtil.sendRequestWithOkHttpClient(ApplicationProperties.musicServlet, musicMap, object : CallBack {
            override fun onFinish(jsonArray: JSONArray) {
                musicFragment.setData(HttpConnUtil.parseJSONtoMusicBean(jsonArray))
            }

            override fun onError(e: Exception) {
                Toast.makeText(this@SearchActivity,"error", Toast.LENGTH_SHORT).show()
            }
        })

        //查询歌手
        val singerMap: ArrayMap<String, String> = ArrayMap()
        singerMap["name"] = searchText
        singerMap["action"] = "findSinger"
        HttpConnUtil.sendRequestWithOkHttpClient(ApplicationProperties.musicServlet, singerMap, object : CallBack {
            override fun onFinish(jsonArray: JSONArray) {
                singerFragment.setData(HttpConnUtil.parseJSONtoSingerBean(jsonArray))
            }

            override fun onError(e: Exception) {
                Toast.makeText(this@SearchActivity,"error", Toast.LENGTH_SHORT).show()
            }
        })

        binding.searchTvSearch.setOnClickListener{
            searchText = binding.searchText.text.toString()
            musicMap["name"] = searchText
            HttpConnUtil.sendRequestWithOkHttpClient(ApplicationProperties.musicServlet, musicMap, object :CallBack {
                override fun onFinish(jsonArray: JSONArray) {
                    musicFragment.setData(HttpConnUtil.parseJSONtoMusicBean(jsonArray))
                    runOnUiThread {
                        musicFragment.resetData()
                    }
                }

                override fun onError(e: Exception) {
                    Toast.makeText(this@SearchActivity,"error", Toast.LENGTH_SHORT).show()
                }
            })

            singerMap["name"] = searchText
            HttpConnUtil.sendRequestWithOkHttpClient(ApplicationProperties.musicServlet, singerMap, object :CallBack {
                override fun onFinish(jsonArray: JSONArray) {
                    singerFragment.setData(HttpConnUtil.parseJSONtoSingerBean(jsonArray))
                    runOnUiThread {
                        singerFragment.resetData()
                    }
                }

                override fun onError(e: Exception) {
                    Toast.makeText(this@SearchActivity,"error", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }
}