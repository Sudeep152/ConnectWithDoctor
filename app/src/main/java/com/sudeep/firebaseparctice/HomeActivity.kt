package com.sudeep.firebaseparctice

import android.R.attr.author
import android.R.attr.description
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sudeep.firebaseparctice.DAO.UserDao
import com.sudeep.firebaseparctice.Model.Users
import kotlinx.android.synthetic.main.activity_home.*
import java.lang.Exception


class HomeActivity : AppCompatActivity() {
    var Imageuri : Uri? =null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var mDatabase=FirebaseDatabase.getInstance()

        var mAuth=FirebaseAuth.getInstance()
        var mDref=mDatabase.getReference("User").child("UserProfile").child(mAuth.uid!!)


        val userName=name.text.toString()




        circleImageView.setOnClickListener {
            ImagePicker.with(this)
                    .crop()	    			//Crop image(Optional), Check Customization for more option
                    .compress(1024)			//Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start()
        }

        saveBtn.setOnClickListener {

     addUser()
            Toast.makeText(this,"Done",Toast.LENGTH_LONG).show()

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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val fileUri = data?.data
         circleImageView.setImageURI(fileUri)

            Imageuri= fileUri

            //You can also get File Path from intent
            val filePath:String = ImagePicker.getFilePath(data)!!
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
    fun addUser(){
        val user=Users(name.text.toString(),father_name.text.toString(), mobile_no.text.toString(),Imageuri.toString(),spouseGender().toString())
        val userDao= UserDao()
        userDao.addUser(user)
    }

}