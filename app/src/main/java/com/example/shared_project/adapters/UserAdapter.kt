package com.example.shared_project.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.shared_project.databinding.ItemRvBinding
import com.example.shared_project.models.User

class UserAdapter(var list: ArrayList<User>, var rvAction: RvAction) :
    RecyclerView.Adapter<UserAdapter.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(user: User, position: Int) {
            itemRvBinding.tvName.text = user.name
            itemRvBinding.tvAge.text = user.age
         itemRvBinding.imageMeny.setOnClickListener{
             rvAction.MyPopupmeny(user,position,itemRvBinding.imageMeny)
         }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int = list.size

}
interface RvAction {
    fun MyPopupmeny(user: User, position: Int, imageView: ImageView)
}