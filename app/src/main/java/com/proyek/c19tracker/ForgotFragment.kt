package com.proyek.c19tracker

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_forgot.*

class ForgotFragment : Fragment() {
    private lateinit var send_button: Button
    private lateinit var email: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_forgot, container, false)
        email = view.findViewById(R.id.email_f) as EditText
        send_button = view.findViewById(R.id.send_button) as Button
        progressBar = view.findViewById(R.id.progressBar2) as ProgressBar

        auth = FirebaseAuth.getInstance()
        send_button.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val email: String = email_f.text.toString().trim{ it <= ' '}
            if (email.isEmpty()) {
                Toast.makeText(activity, "Input Required", Toast.LENGTH_LONG).show()
            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{task ->
                        if(task.isSuccessful ){
                            progressBar.visibility = View.GONE
                            Toast.makeText(activity, "Check Your Email", Toast.LENGTH_LONG).show()
                        }else{
                            progressBar.visibility = View.GONE
                            Toast.makeText(activity, task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
        return view
    }
}