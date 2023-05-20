package com.example.fiver.home

import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fiver.InfoActivity


import com.example.fiver.R




class Catagory_Adapter(val freelancer_list: List<freelancer>) :
    RecyclerView.Adapter<Catagory_Adapter.myview_holder>() {

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myview_holder {
        val viewholder =
            LayoutInflater.from(parent.context).inflate(R.layout.catagory_viewholder, parent, false)
        return myview_holder(viewholder, listener)
    }

    override fun onBindViewHolder(holder: myview_holder, position: Int) {
        val list = freelancer_list[position]

        holder.f_image.setImageResource(R.drawable.placeholder)
        //Glide library
        Glide.with(holder.itemView.context)
            .load(list.image)
            .placeholder(R.drawable.placeholder)
            .into(holder.f_image)
        holder.f_name.text = list.name
        holder.f_work.text = list.work

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, InfoActivity::class.java)
            intent.putExtra("name", list.name)
            intent.putExtra("work", list.work)
            intent.putExtra("image", list.image)
            // Add more data as needed
            holder.itemView.context.startActivity(intent)
        }
    }

    fun updateData(newList: List<freelancer>) {
        var itemList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return freelancer_list.size
    }

    class myview_holder(itemView: View, listener: OnItemClickListener?) :
        RecyclerView.ViewHolder(itemView) {
        val f_image: ImageView = itemView.findViewById(R.id.f_image)
        val f_name: TextView = itemView.findViewById(R.id.f_name)
        val f_work: TextView = itemView.findViewById(R.id.f_work)

        init {
            itemView.setOnClickListener {
                listener?.onItemClick(itemView, adapterPosition)
            }
        }
    }

}


