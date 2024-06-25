package com.example.myapplication.viewUI

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.constraintlayout.widget.R
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CardItemBinding
import com.example.myapplication.room.User

class MyRecyclerViewAdapter(private val usersList: List<User>,
    private val clickListener: (User)-> Unit):
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layputInflater= LayoutInflater.from(parent.context)
        val binding: CardItemBinding= DataBindingUtil.inflate(
            layputInflater,R.layout.abc_action_menu_layout, parent, false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(usersList[position], clickListener)
    }

}

class MyViewHolder(val binding: CardItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(user: User, clickListener:(User)->Unit){
        binding.apply {
            nameTextview.text=user.name
            emailTextview.text=user.email

            listItemLayout.setOnClickListener{
                clickListener(user)
            }
    }


}
}