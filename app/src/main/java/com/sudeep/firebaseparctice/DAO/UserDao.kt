package com.sudeep.firebaseparctice.DAO

import android.provider.Settings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sudeep.firebaseparctice.Model.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDao {

     val database= FirebaseFirestore.getInstance()
     val userCollection=database.collection("Users")
     fun addUser(users: Users){
          users?.let {

               GlobalScope.launch(Dispatchers.IO) {
                    userCollection.document(FirebaseAuth.getInstance().currentUser?.uid!!).set(it)
               }


          }

     }

}