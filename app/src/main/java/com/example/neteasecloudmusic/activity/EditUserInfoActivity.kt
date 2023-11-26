package com.example.neteasecloudmusic.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.neteasecloudmusic.databinding.EditUserinfoLayoutBinding

class EditUserInfoActivity : AppCompatActivity() {
    private lateinit var binding: EditUserinfoLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditUserinfoLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.editUserInfoToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.editUserInfoRlName.setOnClickListener {

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