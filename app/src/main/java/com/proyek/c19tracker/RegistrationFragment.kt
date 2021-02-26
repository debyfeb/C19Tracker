package com.proyek.c19tracker

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.ProgressBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

private const val TAG = "RegistrationFragment"

class RegistrationFragment : Fragment() {
    private lateinit var nama_lengkap: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var login: TextView
    private lateinit var kirim_button: Button
    private lateinit var auth:FirebaseAuth
    private lateinit var progressBar: ProgressBar
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        nama_lengkap = view.findViewById(R.id.nama_lengkap) as EditText
        email = view.findViewById(R.id.email) as EditText
        password = view.findViewById(R.id.password) as EditText
        confirmPassword = view.findViewById(R.id.confirmPassword) as EditText
        login = view.findViewById(R.id.login_skrng) as TextView
        progressBar = view.findViewById(R.id.progressBar) as ProgressBar
        login.setOnClickListener {
            val fragment = LoginFragment()
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container,fragment)
            fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
        }
        kirim_button = view.findViewById(R.id.kirim_button) as Button

        auth = FirebaseAuth.getInstance()
        kirim_button.setOnClickListener{
            progressBar.visibility = View.VISIBLE
            if(nama_lengkap.text.toString().isEmpty() || email.text.toString().isEmpty() || password.text.toString().isEmpty() || confirmPassword.text.toString().isEmpty())
            {
                Toast.makeText(activity, "Input Required", Toast.LENGTH_LONG).show()
            } else if(password.equals(confirmPassword)){
                Toast.makeText(activity, "Confirm Password berbeda", Toast.LENGTH_LONG).show()
            } else{
                createUser(email.text.trim().toString(),password.text.trim().toString())
            }
        }
        return view
    }

    private fun createUser( email:String, password:String){
        activity?.let {
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        progressBar.visibility = View.GONE
                        Toast.makeText(activity, "Register Succesful", Toast.LENGTH_LONG).show()
                        startActivity(Intent(activity, BerandaActivity::class.java))
                        activity!!.finish()
                    } else {
                        progressBar.visibility = View.GONE
                        Toast.makeText(activity, "Failed atau anda sudah terdaftar", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

}