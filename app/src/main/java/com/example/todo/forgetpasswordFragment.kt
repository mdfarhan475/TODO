package com.example.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.todo.databinding.FragmentForgetpasswordBinding
import com.google.firebase.auth.FirebaseAuth


class forgetpasswordFragment : Fragment() {

    lateinit var binding: FragmentForgetpasswordBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {




        binding = FragmentForgetpasswordBinding.inflate(inflater, container, false)

        binding.RP.setOnClickListener {


            val email = binding.EM3.text.toString().trim()

            if (email.isNotEmpty()) {
                sendPasswordResetEmail(email)
            } else {
                binding.EM3.error = "Please enter your email"
            }





        }

        return binding.root
    }

    private fun sendPasswordResetEmail(email: String) {
        val auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Toast.makeText(requireContext(), "Password reset email sent", Toast.LENGTH_SHORT).show()
                } else {

                    Toast.makeText(requireContext(), "Failed to send reset email: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }



}