package com.example.todolist.recyclercomponents


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.todolist.R
import com.example.todolist.data.Database
import com.example.todolist.data.TaskItem
import com.example.todolist.fragments.AllTasksDirections

class CustomAdapter(var list: ArrayList<TaskItem>,var requireActivity:FragmentActivity) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val day: TextView = itemview.findViewById(R.id.day)
        val dayNumber: TextView = itemview.findViewById(R.id.dayNumber)
        val monthName: TextView = itemview.findViewById(R.id.monthName)
        val taskTitle: TextView = itemview.findViewById(R.id.taskTitle)
        val taskDetails: TextView = itemview.findViewById(R.id.taskDetails)
        val wholeCalender: TextView = itemview.findViewById(R.id.wholeCalender)
        val editTask: ImageView = itemview.findViewById(R.id.editTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.day.text = list[position].getDay().substring(0, 3)
        holder.dayNumber.text = list[position].getDate()
        holder.monthName.text = list[position].getMonth().substring(0, 3)
        holder.taskTitle.text = list[position].title
        holder.taskDetails.text = list[position].description
        holder.wholeCalender.text = list[position].getFullDateString()
        var DB: Database
        DB = Room.databaseBuilder(
            requireActivity,
            Database::class.java,
            "task_database"
        ).allowMainThreadQueries().build()
        var id : Int=list[position].id
        holder.itemView.setOnClickListener {
            var action=AllTasksDirections.actionAllTasksFragmentToTaskDetails(
                id
            )
            Navigation.findNavController(it).navigate(action)
    }
        holder.itemView.setOnLongClickListener{
            DB.DaoTaskItem().deleteTaskByID(id)
            list.removeAt(position)
            notifyItemRemoved(position)
            true
        }
    }

}