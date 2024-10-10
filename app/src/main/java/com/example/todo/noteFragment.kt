package com.example.todo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.todo.databinding.FragmentHomeBinding
import com.example.todo.databinding.FragmentNoteBinding
import java.util.Calendar


class noteFragment : Fragment() {


    lateinit var binding: FragmentNoteBinding

    var showTime: String? = null
    var showDate: String? = null

    lateinit var databse : notdatabase




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentNoteBinding.inflate(inflater,container,false)

        databse = Room.databaseBuilder(requireActivity(), notdatabase::class.java, "Note-DB")
            .allowMainThreadQueries().build()

        binding.dateBtn.setOnClickListener {

            PickADate()
        }
        binding.timeBtn.setOnClickListener {

            PickATime()
        }
        binding.submitBtn.setOnClickListener{

            val titleStr = binding.titleET.text.toString()
            val title2Str = binding.titleET2.text.toString()
            val timeStr = showTime ?: "00:00"
            val dateStr = showDate ?: "00/00/0000"

            val note = Note(title = titleStr,title2 = title2Str, time = timeStr, date = dateStr)
            databse.getNoteDao().insertData(note)

            findNavController().navigate(R.id.action_noteFragment_to_homeFragment)

        }









        return binding.root


    }

    private fun PickATime() {
        val calendar = java.util.Calendar.getInstance()

        val minute = calendar.get(java.util.Calendar.MINUTE)
        val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)

        val timePicker = TimePickerDialog(
            requireActivity(), TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->

                 showTime = "$hourOfDay : $minute"
                binding.timeBtn.text = showTime


            }, hour, minute, false

        )
        timePicker.show()
    }

    private fun PickADate() {
        val calender = Calendar.getInstance()

        val day = calender.get(Calendar.DAY_OF_MONTH)
        val month = calender.get(Calendar.MONTH)
        val year = calender.get(Calendar.YEAR)

        val showDatePicker = DatePickerDialog(
            requireActivity(), DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->

                showDate = "$dayOfMonth/${month+1}/$year"
                binding.dateBtn.text = showDate

            }, year, month, day

        )

        showDatePicker.show()
    }


}