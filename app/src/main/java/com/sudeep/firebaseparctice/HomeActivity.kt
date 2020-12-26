package com.sudeep.firebaseparctice

import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var mDatabase=FirebaseDatabase.getInstance()

        var mAuth=FirebaseAuth.getInstance()
        var mDref=mDatabase.getReference("User").child(mAuth.uid!!)

        saveBtn.setOnClickListener {

           mDref.child("Name").setValue(name.text.toString())
           mDref.child("Father Name").setValue(father_name.text.toString())
           mDref.child("Gender").setValue(spouseGender())
            mDref.child("Mobile Number").setValue(mobile_no.text.toString())


        }

    }
     fun spouseGender(): String? {
        val empRdoId: Int = gender.checkedRadioButtonId

        val add: String
        add = if (empRdoId == R.id.Male) {
            "MALE"
        } else if (empRdoId == R.id.Female) {
            "FEMALE"
        } else {
            "Transgender"
        }
        return add
    }

}