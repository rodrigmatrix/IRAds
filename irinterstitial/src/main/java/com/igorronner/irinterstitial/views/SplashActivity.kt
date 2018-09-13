package com.igorronner.irinterstitial.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import com.google.firebase.FirebaseApp
import com.igorronner.irinterstitial.R
import com.igorronner.irinterstitial.init.ConfigUtil
import com.igorronner.irinterstitial.init.IRAds
import com.igorronner.irinterstitial.services.RemoteConfigService
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (ConfigUtil.LOGO > 0)
            logo.setImageResource(ConfigUtil.LOGO)
        else
            logo.visibility = View.GONE

        FirebaseApp.initializeApp(this)
        RemoteConfigService.getInstance(this).canShowSplash { result ->
            if (result!!)
                IRAds.showInterstitial(this)
            else
                finish()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish()
            true
        } else super.onKeyDown(keyCode, event)

    }
}
