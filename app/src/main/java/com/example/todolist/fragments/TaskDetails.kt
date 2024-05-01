package com.example.todolist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.example.todolist.data.Database
import com.example.todolist.databinding.FragmentTaskDetailsBinding

class TaskDetails : Fragment() {
    private var _binding: FragmentTaskDetailsBinding? = null
    private val binding get() = _binding!!
    lateinit var DB: Database
    val args:TaskDetailsArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DB = Room.databaseBuilder(
            requireContext(),
            Database::class.java,
            "task_database"
        ).allowMainThreadQueries().build()
        var taskSelected=DB.DaoTaskItem().selectTaskByID(args.taskid)
        binding.textView.setText(taskSelected.title)
        binding.textView2.setText(taskSelected.description)
        binding.Cal.setText(taskSelected.getFullDateString())
        binding.editButton.setOnClickListener {
            var action=TaskDetailsDirections.actionTaskDetailsToAddNewTask(
                args.taskid
            )
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTaskDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

}