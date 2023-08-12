package com.example.shared_project.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.shared_project.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MysharedPreference {
    private const val NAME = "catch_file_name"
    private const val MODE = Context.MODE_PRIVATE

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var userlist: ArrayList<User>
        get() = gsonStringArrayList(preferences.getString("my_list", "[]")!!)
        set(value) = preferences.edit {
            if (value != null) {
                it.putString("my_list", listToString(value))
            }
        }

    private fun listToString(list: ArrayList<User>): String? {
        return Gson().toJson(list)
    }

    private fun gsonStringArrayList(gsonString: String): ArrayList<User> {
        val list = ArrayList<User>()
        val type = object : TypeToken<ArrayList<User>>() {}.type
        list.addAll(Gson().fromJson(gsonString, type))
        return list
    }

}