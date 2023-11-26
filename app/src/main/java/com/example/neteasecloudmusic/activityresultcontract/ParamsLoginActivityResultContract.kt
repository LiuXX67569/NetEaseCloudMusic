package com.example.neteasecloudmusic.activityresultcontract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.neteasecloudmusic.activity.LoginActivity
import com.example.neteasecloudmusic.bean.UserInfo

class ParamsLoginActivityResultContract: ActivityResultContract<UserInfo, UserInfo>() {
    override fun createIntent(context: Context, input: UserInfo): Intent {
        return Intent(context, LoginActivity::class.java).apply {
            putExtra("data", input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): UserInfo {
        var userInfo = UserInfo()
        when (resultCode) {
            Activity.RESULT_OK -> {
                userInfo = intent?.getSerializableExtra("result") as UserInfo
            }
        }

        return userInfo
    }
}