package com.example.waveoffood

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.waveoffood.databinding.ActivityChooselocationBinding

class chooselocationActivity : AppCompatActivity() {
    private val binding:ActivityChooselocationBinding by lazy{
        ActivityChooselocationBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val locationList= arrayOf("hyd","banglore","chennai","mumbai ")
//        val locationList= arrayOf("hyd","banglore","chennai")
        val adapter=ArrayAdapter(this, android.R.layout.simple_list_item_1,locationList)
        val autoCompleteTextView=binding.listOfLocation
        autoCompleteTextView.setAdapter(adapter)


        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            val selectedLocation = locationList[position]
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("selected_location", selectedLocation)
            startActivity(intent)
            finish() // This ensures that `ChooselocationActivity` doesn’t stay in the background.
        }

    }
}