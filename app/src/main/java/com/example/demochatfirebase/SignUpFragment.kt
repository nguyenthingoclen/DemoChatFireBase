package com.example.demochatfirebase

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.Intent.getIntent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_signup.*
import java.lang.Exception

class SignUpFragment : Fragment(){

    private var auth : FirebaseAuth?= null
    lateinit var reference : DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        registerBtn.setOnClickListener {
            auth?.createUserWithEmailAndPassword(emailRegisterEdit.text.toString(), passWordRegisterEdit.text.toString())
                ?.addOnCompleteListener(activity!!) { p0 ->
                    if (p0.isSuccessful) {
                        var firebaseUser = auth!!.currentUser
                        var userId = firebaseUser?.uid

                        reference = FirebaseDatabase.getInstance().getReference("Users").child(userId!!)
                        val user = HashMap<String,String>()
                        user.put("id",userId)
                        user.put("username",userNameRegisterEdit.text.toString())
                        user.put("avatarURL","default")

                        reference.setValue(user).addOnCompleteListener(object : OnCompleteListener<Void>{
                            override fun onComplete(p0: Task<Void>) {
                                view.findNavController().navigate(R.id.chatFragment)
                            }

                        })

                    }
                }
                ?.addOnFailureListener(activity!!) { p0 ->
                    Toast.makeText(context, p0.message.toString(), Toast.LENGTH_SHORT).show() }
        }


    }



}






