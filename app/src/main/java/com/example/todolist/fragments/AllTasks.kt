package com.example.todolist.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.todolist.data.Database
import com.example.todolist.data.TaskItem
import com.example.todolist.databinding.FragmentAllTasksBinding
import com.example.todolist.recyclercomponents.CustomAdapter

class AllTasks : Fragment() {
    private var _binding: FragmentAllTasksBinding? = null
    private val binding get() = _binding!!
    lateinit var itemList: ArrayList<TaskItem>
    lateinit var DB: Database
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DB = Room.databaseBuilder(
            requireContext(),
            Database::class.java,
            "task_database"
        ).allowMainThreadQueries().build()
        itemList= ArrayList(DB.DaoTaskItem().getTasks())
        Log.d("alltasks",itemList.toString())
        binding.rv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rv.adapter= CustomAdapter(itemList, requireActivity())
        if (itemList.size!=0){
            binding.zeroStateText.isVisible=false
        }
        binding!!.newTaskButton.setOnClickListener {
            val action = AllTasksDirections.actionAllTasksFragmentToAddNewTask(
                0
            )
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAllTasksBinding.inflate(inflater, container, false)
        return binding.root
    }
}