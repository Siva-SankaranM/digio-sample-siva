package com.example.sivatest

import `in`.digio.sdk.kyc.DigioEnvironment
import `in`.digio.sdk.kyc.DigioKycConfig
import `in`.digio.sdk.kyc.DigioNativeSession
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import android.widget.Button
import android.widget.Toast




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // get reference to button
        val btn_click_me = findViewById<Button>(R.id.kyc_button)
        // set on-click listener
        btn_click_me.setOnClickListener {
            // your code to perform when the user clicks on the button
            kycStart()
            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
        }
    }
     fun kycStart (){
        try {
            val config = DigioKycConfig()
            config.setEnvironment(DigioEnvironment.PRODUCTION)
//            config.setPrimaryColor(Color.parseColor("#17c39b"))
//            config.setSecondaryColor(Color.parseColor("#B4E9D8"))
            val session = DigioNativeSession()
            session.init(this@MainActivity, config, "clientId", "clientSecret")
            session.startNativeSession()
        } catch (e: Exception) {
        }
    }
    fun onDigioNativeKycSuccess(data: JSONObject) {
        Log.e("Digio called", "Success")
    }

    fun onDigioNativeKycFailure(response: JSONObject) {
        Log.e("Digio called", "Failure")
    }

    fun onDigioNativeEventTracker(eventObj: JSONObject) {
        Log.e("Digio called", eventObj.toString())
    }
}