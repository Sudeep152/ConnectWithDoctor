package com.sudeep.firebaseparctice

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class Splash_Screen : AppCompatActivity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash__screen)

        handler = Handler()
        handler.postDelayed({
            var sharepref =getSharedPreferences("checklogin", MODE_PRIVATE)

            var  check: String = sharepref.getString("FLAG", "FALSE")!!
            if (check == "TRUE"){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()


            }else {

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
          }, 3000)

    }
}