package com.example.demochatfirebase

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.emailETx
import java.lang.Exception

class LoginFragment :Fragment(){

    private var auth = FirebaseAuth.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actionToSignUpFr  = Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_signUpFragment)
        signUpTx.setOnClickListener(actionToSignUpFr)
        Log.d("TAG:LoginFragment", auth!!.currentUser?.displayName.toString())
        loginBtn.setOnClickListener {
            auth.signInWithEmailAndPassword(emailETx.text.toString(), passETx.text.toString())
                .addOnCompleteListener(activity!!, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(p0: Task<AuthResult>) {
                        if (p0.isSuccessful) {
                            Toast.makeText(context, auth.currentUser?.email, Toast.LENGTH_SHORT).show()
                            val bundle = Bundle()
                            bundle.putString("email",auth.currentUser?.email)
                            bundle.putString("avatar",auth.currentUser?.photoUrl.toString())
                            view.findNavController().navigate(R.id.chatFragment,bundle)
                        }
                    }
                })
                .addOnFailureListener(activity!!, object : OnFailureListener {
                    override fun onFailure(p0: Exception) {
                        Toast.makeText(context, p0.message.toString(), Toast.LENGTH_SHORT).show()
                    }

                })
            Util.hideKeyboard(context!!,it)
        }
    }

}