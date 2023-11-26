package com.example.neteasecloudmusic.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.neteasecloudmusic.bean.UserInfo
import com.example.neteasecloudmusic.databinding.LoginLayoutBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginTvEnterNow.setOnClickListener {
            val intent = Intent()
            setResult(RESULT_CANCELED, intent)
            finish()
        }

        binding.loginBtnLogin.setOnClickListener {
            val email = binding.loginEdtTxtEmail.text.toString()
            val password = binding.loginEdtTxtPassword.text.toString()
            if (binding.loginChkReadme.isChecked) {
                val duser = login(email, password)
                if (duser != UserInfo()) {
                    val intent = Intent()
                    intent.putExtra("result", duser)
                    setResult(RESULT_OK, intent)
                    finish()
                } else {
                    Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "请勾选阅读协议", Toast.LENGTH_SHORT).show()
            }
        }

        binding.loginTvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
    private fun login(email:String, password:String): UserInfo {
        var userInfo = UserInfo()
        //向服务器请求验证数据
        userInfo = UserInfo(1, "test", "123456789ab", 0, "", "", "")
        return userInfo
    }
}
