package com.example.todo

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.todo.databinding.FragmentSigninBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.zip.Inflater


class signinFragment : Fragment() {

    lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentSigninBinding.inflate(inflater, container, false)

        binding.SING.setOnClickListener {


            val email = binding.EM2.text.toString().trim()
            val password = binding.pass.text.toString().trim()

            if(isEmailValid(email) && isPasswordValid(password)){
                signUpUser(email, password)
            }else{
                Toast.makeText(requireContext(), "Invalid email or Password", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signin.setOnClickListener {
            findNavController().navigate(R.id.action_signinFragment_to_loginFragment)
        }



        return binding.root
    }

    private fun signUpUser(email: String, password: String) {
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                Toast.makeText(
                    requireContext(),
                    "Sign Up Successfully ${user?.email}",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_signinFragment_to_homeFragment)
            }else{
                Toast.makeText(requireContext(), "${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {

        return password.length >= 8
    }



}