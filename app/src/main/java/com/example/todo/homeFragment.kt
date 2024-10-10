package com.example.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.todo.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth


class homeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var databse : notdatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentHomeBinding.inflate(inflater,container,false)

        databse = Room.databaseBuilder(requireActivity(), notdatabase::class.java, "Note-DB")
            .allowMainThreadQueries().build()

       var notes : List<Note> = databse.getNoteDao().getAllData()

        var adaptr = noteadapter()
        adaptr.submitList(notes)

        binding.recyclerView.adapter = adaptr


        binding.addBtn.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment_to_noteFragment)
        }

        binding.logoutBtn.setOnClickListener {
            var ath = FirebaseAuth.getInstance()
            FirebaseAuth.getInstance().signOut()

            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }









        return binding.root
    }


}