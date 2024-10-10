package com.example.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.todo.databinding.ItemDesignBinding

class noteadapter :ListAdapter <Note, noteviewholder>(comparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noteviewholder {
        return noteviewholder(
            ItemDesignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false

            )
        )
    }

    override fun onBindViewHolder(holder: noteviewholder, position: Int) {
        getItem(position).let {

            holder.binding.apply {

                noteTitle.text = it.title
                noteTitle2.text = it.title2
                DateTV.text = it.date
                TimeTV.text= it.time

            }

        }
    }

    companion object{


        var comparator = object : DiffUtil.ItemCallback<Note>(){
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

        }


    }
}