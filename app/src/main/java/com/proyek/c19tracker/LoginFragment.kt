package com.proyek.c19tracker

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*


import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var loginButton: Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var auth:FirebaseAuth
    private lateinit var progressBar: ProgressBar
    private lateinit var forget: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        email = view.findViewById(R.id.email) as EditText
        password = view.findViewById(R.id.password) as EditText
        progressBar = view.findViewById(R.id.progressBar) as ProgressBar
        loginButton = view.findViewById(R.id.login_button) as Button
        auth = FirebaseAuth.getInstance()
        forget = view.findViewById(R.id.forgot_password) as TextView
        loginButton.setOnClickListener{
            progressBar.visibility = View.VISIBLE
            if( email.text.toString().isEmpty() || password.text.toString().isEmpty())
            {
                Toast.makeText(activity, "Input Required", Toast.LENGTH_LONG).show()
            } else{
                signIn(email.text.trim().toString(),password.text.trim().toString())
            }
        }
        forget.setOnClickListener {
            val fragmentF = ForgotFragment()
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container,fragmentF)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
        return view
    }

    private fun signIn(email:String, password: String){
        activity?.let {
            progressBar.visibility = View.VISIBLE
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(it){ task->
                    if(task.isSuccessful){
                        progressBar.visibility = View.VISIBLE
                        startActivity(Intent(activity,BerandaActivity::class.java))
                        activity!!.finish()
                    } else{
                       Toast.makeText(activity, "Error!!" +task.exception, Toast.LENGTH_LONG).show()
                        progressBar.visibility = View.GONE
                    }
                }
        }
    }


}