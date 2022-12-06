package com.eyyuperdogan.personregister.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eyyuperdogan.personregister.MainActivity
import com.eyyuperdogan.personregister.PersonAdd
import com.eyyuperdogan.personregister.databinding.RowBindingBinding
import com.eyyuperdogan.personregister.model.User

class UserAdapter(val userList:List<User>): RecyclerView.Adapter<UserAdapter.userAdapter>() {
    class userAdapter(val recyclerRowBinding: RowBindingBinding): RecyclerView.ViewHolder(recyclerRowBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userAdapter {
        var recyclerRowBinding=RowBindingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return userAdapter(recyclerRowBinding)
    }

    override fun onBindViewHolder(holder: userAdapter, position: Int) {

        holder.recyclerRowBinding.recyclerViewTextView.text=userList.get(position).name
        holder.itemView.setOnClickListener(){
            val intent= Intent(holder.itemView.context,PersonAdd::class.java)
            intent.putExtra("username",userList.get(position).name)
            intent.putExtra("numberPhone",userList.get(position).numberPhone)
            intent.putExtra("users",userList.get(position))

            intent.putExtra("info","new")
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}