package com.example.neteasecloudmusic.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.view.isVisible
import com.example.neteasecloudmusic.databinding.SignupLayoutBinding

class SignUpActivity : ComponentActivity() {
    private lateinit var binding: SignupLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignupLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var isEqual = false
        binding.signUpEdtTxtSignPwdAgain.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val pwd1 = binding.signUpEdtTxtSignPwd.text.toString()
                val pwd2 = binding.signUpEdtTxtSignPwdAgain.text.toString()
                isEqual = (pwd1 == pwd2)
                if (!isEqual) {
                    binding.signUpEdtTxtSignPwd.text.clear()
                    binding.signUpEdtTxtSignPwdAgain.text.clear()
                    Toast.makeText(this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.signUpBtnSignSure.setOnClickListener {
            val username = binding.signUpEdtTxtUsername.text.toString()
            val email = binding.signUpEdtTxtSignEmail.text.toString()
            //将信息提交到服务器然后返回
            if (binding.signUpLlGroupPwd.isVisible) {
                if (isEqual) {
                    val pwd = binding.signUpEdtTxtSignPwd.text.toString()
                    finish()
                }
            } else {
                finish()
            }
        }

        binding.signUpBtnCancel.setOnClickListener {
            finish()
        }
    }
}