package com.example.shared_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import com.example.shared_project.adapters.RvAction
import com.example.shared_project.adapters.UserAdapter
import com.example.shared_project.databinding.ActivityMainBinding
import com.example.shared_project.models.User
import com.example.shared_project.utils.MysharedPreference

class MainActivity2 : AppCompatActivity(),RvAction {
    private lateinit var binding: ActivityMainBinding
    private lateinit var list :  ArrayList<User>
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnAdd.setOnClickListener {
                val intent = Intent(this@MainActivity2,AddActivity::class.java)
                intent.putExtra("key",true)//true.agar add bolsa
                startActivity(intent)
            }
        }
    }
    override fun onResume() {
        super.onResume()
        MysharedPreference.init(this)
        list = MysharedPreference.userlist
        userAdapter = UserAdapter(list, this)
        binding.rv.adapter = userAdapter
    }
    override fun MyPopupmeny(user: User, position: Int, imageView: ImageView) {
        var popupMenu = PopupMenu(this, imageView)
        popupMenu.inflate(R.menu.my_meny)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.meny_edit -> {
                    val intent = Intent(this,AddActivity::class.java)
                    intent.putExtra("key",false)//false.agar edit bolsa
                    intent.putExtra("position",position)
                    startActivity(intent)
                    Toast.makeText(this, "edit", Toast.LENGTH_SHORT).show()
                }
                R.id.meny_delete -> {
                    list.remove(user)
                    MysharedPreference.userlist = list
                    userAdapter.list = list
                    userAdapter.notifyItemRemoved(position)
                    Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
        popupMenu.show()
    }
}