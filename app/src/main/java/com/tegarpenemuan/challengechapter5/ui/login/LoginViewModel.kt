package com.tegarpenemuan.challengechapter5.ui.login

import android.content.Intent
import android.content.SharedPreferences
import android.util.Patterns
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.tegarpenemuan.challengechapter5.Constant
import com.tegarpenemuan.challengechapter5.MainActivity
import com.tegarpenemuan.challengechapter5.data.ErrorResponse
import com.tegarpenemuan.challengechapter5.network.AuthApiClient
import com.tegarpenemuan.myapplication.data.api.auth.SignInRequest
import com.tegarpenemuan.myapplication.data.local.UserEntity
import com.tegarpenemuan.myapplication.database.MyDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel() : ViewModel() {
    private var db: MyDatabase? = null
    private var pref: SharedPreferences? = null

    private var email: String = ""
    private var password: String = ""

    val shouldShowError: MutableLiveData<String> = MutableLiveData()
    val shouldOpenHomePage: MutableLiveData<Boolean> = MutableLiveData()

    fun onViewLoaded(db: MyDatabase, preferences: SharedPreferences) {
        this.db = db
        this.pref = preferences
    }

    fun onChangeEmail(email: String) {
        this.email = email
    }

    fun onChangePassword(password: String) {
        this.password = password
    }

    fun onClickSignIn() {
        if (email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            shouldShowError.postValue("User tidak valid")
        } else if (password.isEmpty() && password.length < 8) {
            shouldShowError.postValue("Password tidak valid")
        } else {
            signInFromAPI()
        }
    }

    private fun signInFromAPI() {
        CoroutineScope(Dispatchers.IO).launch {
            val request = SignInRequest(
                login = email,
                password = password
            )
            val response = AuthApiClient.instanceAuth.signIn(request)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val signInResponse = response.body()
                    signInResponse?.let {
                        // mempersiapkan untuk simpan token
                        insertToken(it.userToken.orEmpty())

                        // mempersiapkan untuk insert ke database
                        val userEntity = UserEntity(
                            id = it.objectId.orEmpty(),
                            name = it.name.orEmpty(),
                            email = it.email.orEmpty(),
                            job = it.job.orEmpty(),
                            image = it.image.orEmpty()
                        )
                        insertProfile(userEntity)
                    }
                } else {
                    val error =
                        Gson().fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                    shouldShowError.postValue(error.message.orEmpty() + " #${error.code}")
                }
            }
        }
    }

    private fun insertToken(token: String) {
        if (token.isNotEmpty()) {
            pref?.edit {
                putString(Constant.Preferences.KEY.TOKEN, token)
                apply()
            }
        }
    }

    private fun insertProfile(userEntity: UserEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = db?.userDAO()?.insertUser(userEntity)
            withContext(Dispatchers.Main) {
                if (result != 0L) {
                    shouldOpenHomePage.postValue(true)
                } else {
                    shouldShowError.postValue("Maaf, Gagal insert ke dalam database")
                }
            }
        }
    }
}