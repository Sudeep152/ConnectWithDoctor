package com.sudeep.firebaseparctice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
       switchToSignUp.setOnClickListener { val gotoSignUp=Intent(this,Signupctivity::class.java)
       startActivity(gotoSignUp)
           finish()
       }
        var mAuth=FirebaseAuth.getInstance()

        logIn_Btn.setOnClickListener {
            if(TextUtils.isEmpty(l_email.text.toString())||
            TextUtils.isEmpty(l_pass.text.toString())){
                Toast.makeText(this,"Please enter all fields", Toast.LENGTH_SHORT).show()
            }
            else{
                var email=l_email.text.toString()
                var pass=l_pass.text.toString()





                mAuth.signInWithEmailAndPassword(email,pass)
                    .addOnCompleteListener { task->
                        if(task.isSuccessful){
                            Toast.makeText(this,"Welcome Back",Toast.LENGTH_SHORT).show()
                            var sharepref =getSharedPreferences("checklogin", MODE_PRIVATE)
                            var ed=sharepref.edit()
                            ed.putString("FLAG","TRUE")
                            ed.apply()

                            val intent =Intent(this,HomeActivity::class.java)
                            startActivity(intent)
                            finish()

                        }else{

                            Toast.makeText(this, "Error occurred", Toast.LENGTH_LONG).show()
                        }

                    }


            }



        }


    }
}