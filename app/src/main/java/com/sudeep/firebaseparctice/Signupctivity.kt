package com.sudeep.firebaseparctice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signupctivity.*

class Signupctivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signupctivity)
        switchToLogIn.setOnClickListener { val gotoLogin=Intent(this,LoginActivity::class.java)
        startActivity(gotoLogin)
        finish()}

        var mAuth=FirebaseAuth.getInstance()


        reg_Btn.setOnClickListener {

            if(TextUtils.isEmpty(r_email.text.toString())||
                TextUtils.isEmpty(r_pass.text.toString())||
                TextUtils.isEmpty(r_passre.text.toString())){
                Toast.makeText(this,"Please enter all fields",Toast.LENGTH_SHORT).show()
            }
            else if (r_pass.text.toString().length < 6|| !r_pass.text.contains("@")){
                Toast.makeText(this,"Your Password must be greater than 6 and Contains @",Toast.LENGTH_SHORT).show()
            }
           else if(r_pass.text.toString() !=r_passre.text.toString()){
                Toast.makeText(this,"Password must be same",Toast.LENGTH_SHORT).show()
            }

            else {
                mAuth.createUserWithEmailAndPassword(r_email.text.toString(), r_pass.text.toString())
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Registration successful.", Toast.LENGTH_LONG).show()
                                val intent =Intent(this,LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, "Please try again."+task.exception, Toast.LENGTH_LONG).show()
                                println(task.exception)
                            }
                        }


            }


        }

    }

}