package com.tegarpenemuan.challengechapter5.ui.profile

import android.content.Entity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tegarpenemuan.myapplication.database.MyDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private var db: MyDatabase? = null
//    val shouldShowDataUser: MutableLiveData<List<Data>> = MutableLiveData()

//    fun getUser() {
//        CoroutineScope(Dispatchers.IO).launch {
//            val user = db?.userDAO()?.getUsername("tegarpenemuan@gmail.com")
//            user?.let {
//                val Data = Entity(
//
//                )
//                shouldShowDataUser.postValue(List(it))
//            } ?: run {
//                shouldShowDataUser.postValue("Data Kosong")
//            }
//
//        }
//    }
}