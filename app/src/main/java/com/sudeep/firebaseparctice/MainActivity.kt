package com.sudeep.firebaseparctice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signoutBtn.setOnClickListener {
            var mAuth=FirebaseAuth.getInstance();
            mAuth.signOut()
            val intent =Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
            var sharepref =getSharedPreferences("checklogin", MODE_PRIVATE)
            var ed=sharepref.edit()
            ed.putString("FLAG","FALSE")
            ed.apply()

        }
    }
}