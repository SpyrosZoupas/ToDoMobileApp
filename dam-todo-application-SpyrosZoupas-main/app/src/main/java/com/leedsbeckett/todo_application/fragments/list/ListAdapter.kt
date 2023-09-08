package com.leedsbeckett.todo_application.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.leedsbeckett.todo_application.R
import com.leedsbeckett.todo_application.model.Task

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    // Store Task objects to be displayed in RecyclerView
    private var taskList = emptyList<Task>()

    // ViewHolder class to hold the views of each item in the RecyclerView
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    // Called when the ViewHolder is created
    // Inflate the layout of each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_row, parent, false))
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    // Bind the data to the views of each item in the RecyclerView
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Get the Task object at the current position
        val currentItem = taskList[position]

        // Set the name of the Task to the TextView in the item layout
        holder.itemView.findViewById<TextView>(R.id.name_txt).text = currentItem.name

        // OnClickListener to the item layout, called when user clicks on a task
        // Navigate to the UpdateFragment
        holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    // Update the list of Task objects to be displayed in the RecyclerView
    fun setData(task: List<Task>){
        this.taskList = task
        notifyDataSetChanged()
    }
}