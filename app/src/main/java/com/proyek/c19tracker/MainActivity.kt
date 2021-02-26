package com.proyek.c19tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentRegister = RegistrationFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragmentRegister)
            .commit()
    }


}