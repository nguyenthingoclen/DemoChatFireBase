package com.example.demochatfirebase

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_chat.*
import java.util.*
import kotlin.collections.HashMap


class ChatFragment :Fragment(){
    private var auth = FirebaseAuth.getInstance()
    private var database = FirebaseDatabase.getInstance()
    private var emailUser :String = ""
    private var messList = mutableListOf<ChatData>()
    lateinit var adapter: Adapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat,container,false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emailUser = FirebaseAuth.getInstance().currentUser?.email.toString()
       // avatarUser = arguments?.getString("avatar")!!


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = Adapter(messList,context!!)
        chatRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        chatRecyclerView.adapter = adapter
//        var reference = database.reference
//        reference.addValueEventListener(object :ValueEventListener{
//            override fun onDataChange(p0: DataSnapshot) {
//                messList.clear()
//                Log.d("CHAT","SUCCESS!")
//                for (item in p0.children){
//                    var data :HashMap<String,String>  = item.value as HashMap<String, String>
//                    Log.d("CHAT","data: ${data.get("mess")}")
//                    var mess = ChatData()
//                    mess.name = data["name"]
//                    mess.mess = data["mess"]
//                    messList.add(mess)
//                }
//                adapter.notifyDataSetChanged()
//            }
//
//            override fun onCancelled(p0: DatabaseError) {
//                Log.d("CHAT","Error! : ${p0.message}")
//            }
//
//        })

//        sendImg.setOnClickListener{
//            var data = ChatData()
//            data.mess = messageTx.text.toString()
//            data.name= emailUser
//            Log.d("CHAT","name user! : ${data.name}")
//            //mReference.child(String.valueOf(Date().time)).setValue(data)
//            reference.child(Date().time.toString()).setValue(data)
//            //reference.push().setValue(data)
//            messageTx.setText("")
//
//        }

        logoutTx.setOnClickListener {
            auth.signOut()
            view.findNavController().navigate(R.id.loginFragment)
        }

    }
}