package com.example.todo

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.todo.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class loginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var firebaseUser: FirebaseUser


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        FirebaseAuth.getInstance().currentUser?.let {
            firebaseUser = it
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

        }
        binding.loginButton.setOnClickListener {

            val email = binding.EM.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (isEmailValid (email) && isPasswordValid(password)) {
                loginUser(email, password)

            }else{

                Toast.makeText(requireContext(), "Invalid email or Password", Toast.LENGTH_SHORT).show()

            }


        }
        binding.signup.setOnClickListener {

            findNavController().navigate(R.id.action_loginFragment_to_signinFragment)
        }

        binding.fgtpass.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgetpasswordFragment)
        }



        return binding.root
    }
    private fun loginUser(email: String, password: String) {
        val ath = FirebaseAuth.getInstance()
        ath.signInWithEmailAndPassword(email, password).addOnCompleteListener { tesk ->
            if (tesk.isSuccessful) {
                val user = ath.currentUser
                Toast.makeText(
                    requireContext(),
                    "Login Successfully ${user?.email} ",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Toast.makeText(requireContext(), "${tesk.exception?.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    private fun isPasswordValid(password: String): Boolean {

        return password.length >= 8
    }
    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }



}