package com.example.waveoffood

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.waveoffood.databinding.ActivityLoginBinding
import com.example.waveoffood.databinding.ActivitySigninBinding

class signinActivity : AppCompatActivity() {
    private val binding: ActivitySigninBinding by lazy{
        ActivitySigninBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_login)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        binding.loginbutton.setOnClickListener{
//            val intent= Intent(this, signinActivity::class.java)
//            startActivity(intent)
//        }
//        binding.donthavebutton.setOnClickListener{
//            val intent= Intent(this, signinActivity::class.java)
//            startActivity(intent)
//        }
        binding.alreadyHaveAccount.setOnClickListener{
            val intent=Intent(this,loginActivity::class.java)
            startActivity(intent)
        }

    }
}