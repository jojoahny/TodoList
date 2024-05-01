package com.example.todolist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.example.todolist.data.Database
import com.example.todolist.data.TaskItem
import com.example.todolist.databinding.FragmentAddNewTaskBinding
import com.google.android.material.datepicker.MaterialDatePicker

class AddNewTask : Fragment() {
    private var _binding: FragmentAddNewTaskBinding? = null
    private val binding get() = _binding!!
    lateinit var DB: Database
    var getDate: Long = 0
    val args:AddNewTaskArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val datePicker = MaterialDatePicker.Builder.datePicker().build()

        binding.dateTextInputLayout.setEndIconOnClickListener {
            datePicker.apply {
                show(this@AddNewTask.requireActivity().supportFragmentManager, "date_picker")
                addOnPositiveButtonClickListener {
                    getDate = it
                    binding.dateTextInput.setText(this.headerText)
                }
            }
        }
        DB = Room.databaseBuilder(
            requireContext(),
            Database::class.java,
            "task_database"
        ).allowMainThreadQueries().build()
        if (args.idd!=0){
            var selectedItem= DB.DaoTaskItem().selectTaskByID(args.idd)
            binding.edit1.setText(selectedItem.title)
            binding.edit2.setText(selectedItem.description)
            binding.dateTextInput.setText(selectedItem.getFullDateString())
        }

        binding.saveButton.setOnClickListener {
            DB.DaoTaskItem().insertTask(TaskItem(args.idd,binding.edit1.text.toString(),binding.edit2.text.toString(),getDate))
            val action = AddNewTaskDirections.actionAddNewTaskToAllTasksFragment()
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }
}