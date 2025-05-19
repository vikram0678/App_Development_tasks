package com.example.waveoffood

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.waveoffood.databinding.ActivityStartBinding

class startActivity : AppCompatActivity() {
    private val binding: ActivityStartBinding by lazy {
        ActivityStartBinding.inflate(layoutInflater)
}
        override fun onCreate(savedInstanceState: Bundle?){
            super.onCreate(savedInstanceState)
        setContentView(binding.root)
            binding.nextButton.setOnClickListener {
                val intent = Intent(this,loginActivity::class.java)
                startActivity(intent)
            }
        }

}