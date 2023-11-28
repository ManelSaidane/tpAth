package com.example.tplogin


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.example.tplogin.Login
import com.example.tplogin.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var actionToggle: ActionBarDrawerToggle
    lateinit var user: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        initMenu(binding)
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        if (user == null) {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        } else {
            binding.tvDetails.text = user!!.email.toString()
        }
        binding.btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initMenu(binding: ActivityMainBinding) {

            drawerLayout = binding.Drawer
            navView = binding.navView

            actionToggle = ActionBarDrawerToggle(this, drawerLayout, binding.toolbar, 0, 0)
            drawerLayout.addDrawerListener(actionToggle)

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

//make icon menu visible
            actionToggle.syncState()
        }
    }


