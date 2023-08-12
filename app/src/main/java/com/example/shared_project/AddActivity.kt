package com.example.shared_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.shared_project.databinding.ActivityAddBinding
import com.example.shared_project.models.User
import com.example.shared_project.utils.MysharedPreference

class AddActivity : AppCompatActivity() {
    var isAdd = true
    var position = -1
    private lateinit var list: ArrayList<User>
    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MysharedPreference.init(this)
        isAdd = intent.getBooleanExtra("key", true)
        if (!isAdd) {
            position = intent.getIntExtra("position", -1)
            list = MysharedPreference.userlist
            binding.edtName.setText(list[position].name)
            binding.edtAge.setText(list[position].age)
        }

        binding.apply {
            btnKeyingi.setOnClickListener {
                if (isAdd) {
                    val user = User(edtName.text.toString(), edtAge.text.toString())
                    val list = MysharedPreference.userlist
                    list.add(user)
                    MysharedPreference.userlist = list
                    Toast.makeText(this@AddActivity, "saqlandi", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    val user = User(edtName.text.toString(), edtAge.text.toString())
                    list[position] = user
                    MysharedPreference.userlist = list
                    Toast.makeText(this@AddActivity, "edited", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}