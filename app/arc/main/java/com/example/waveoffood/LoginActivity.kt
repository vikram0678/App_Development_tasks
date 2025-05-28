//package com.example.waveoffood
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import com.example.waveoffood.databinding.ActivityLoginBinding
//
//class loginActivity : AppCompatActivity() {
//    private val binding:ActivityLoginBinding by lazy{
//        ActivityLoginBinding.inflate(layoutInflater)
//    }
//    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        enableEdgeToEdge()
////        setContentView(R.layout.activity_login)
////        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
////            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
////            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
////            insets
////        }
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//        binding.loginbutton.setOnClickListener{
//            val intent= Intent(this, SignupActivity::class.java)
//            startActivity(intent)
//        }
//        binding.donthavebutton.setOnClickListener{
//            val intent= Intent(this, SignupActivity::class.java)
//            startActivity(intent)
//        }
////        binding.alreadyHaveAccount.setOnClickListener{}
//
//    }
//}



package com.example.waveoffood

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.waveoffood.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Handle Login button click
        binding.loginbutton.setOnClickListener {
            val email = binding.loginUserEmail.text.toString().trim()
            val password = binding.loginUserPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Authenticate user with Firebase
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // User authenticated, now fetch user details
                        val userId = auth.currentUser?.uid ?: return@addOnCompleteListener
                        val database = FirebaseDatabase.getInstance().reference.child("users").child(userId)
                        database.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (snapshot.exists()) {
                                    // User exists in database, get username
                                    val username = snapshot.child("username").getValue(String::class.java) ?: "User"
                                    Toast.makeText(this@LoginActivity, "Login successful!", Toast.LENGTH_SHORT).show()
                                    // Navigate to WelcomeActivity
                                    val intent = Intent(this@LoginActivity, chooselocationActivity::class.java)
                                    intent.putExtra("USERNAME", username)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    // User not found in database
                                    Toast.makeText(this@LoginActivity, "User not found. Please create an account.", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                Toast.makeText(this@LoginActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                            }
                        })
                    } else {
                        // Login failed
                        Toast.makeText(this, "Login failed: ${task.exception?.message}. Please create an account.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // Navigate to SignupActivity if user doesn't have an account
        binding.donthavebutton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}