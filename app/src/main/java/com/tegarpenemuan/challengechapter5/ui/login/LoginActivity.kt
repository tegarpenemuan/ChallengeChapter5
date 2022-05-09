package com.tegarpenemuan.challengechapter5.ui.login

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.tegarpenemuan.challengechapter5.Constant
import com.tegarpenemuan.challengechapter5.MainActivity
import com.tegarpenemuan.challengechapter5.databinding.ActivityLoginBinding
import com.tegarpenemuan.challengechapter5.ui.register.RegisterActivity
import com.tegarpenemuan.myapplication.database.MyDatabase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = MyDatabase.getInstance(this.applicationContext)
        val pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)

        viewModel.onViewLoaded(db, pref)

        bindViewModel()
        bindView()

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        val pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)

        if (pref.getBoolean(Constant.Preferences.KEY.IS_LOGIN,false)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun bindViewModel() {
        viewModel.shouldShowError.observe(this) {
            val snackbar = Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
            snackbar.view.setBackgroundColor(Color.RED)
            snackbar.show()
        }

        viewModel.shouldOpenHomePage.observe(this) {
            if (it) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun bindView() {
        binding.etUsername.doAfterTextChanged {
            viewModel.onChangeEmail(it.toString())
        }

        binding.etPassword.doAfterTextChanged {
            viewModel.onChangePassword(it.toString())
        }

        binding.btnLogin.setOnClickListener {
            viewModel.onClickSignIn()
        }
    }
}