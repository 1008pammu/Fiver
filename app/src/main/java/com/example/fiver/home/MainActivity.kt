package com.example.fiver.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fiver.R
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {
    private lateinit var  catagory_recycler:RecyclerView
    private lateinit var dbref: DatabaseReference
    val freelancer_list:MutableList<freelancer> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        catagory_recycler=findViewById(R.id.catagory_recycler)


        catagory_recycler.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val adapter= Catagory_Adapter(freelancer_list)
        catagory_recycler.adapter=adapter


        getuserdata();

    }

    private fun getuserdata() {
        dbref=FirebaseDatabase.getInstance().getReference("freelancer")
        dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (freelancerSnapshot in snapshot.children){
                        val freelancer = freelancerSnapshot.getValue(freelancer::class.java)
                        if (freelancer != null) {
                            freelancer_list.add(freelancer)
                            Log.d("Firebase Data", freelancer.toString())
                        }

                    }
                    val adapter= Catagory_Adapter(freelancer_list)
                    catagory_recycler.adapter=adapter

                    adapter.notifyDataSetChanged()

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }

        )
    }}