package com.leedsbeckett.todo_application.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.leedsbeckett.todo_application.R
import com.leedsbeckett.todo_application.model.Task
import com.leedsbeckett.todo_application.viewmodel.TaskViewModel

class UpdateFragment : Fragment()
{
    // Get current task values from the navigation component
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mTaskViewModel: TaskViewModel

    // Create fragment view
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        // Set the text in the EditText views to the current task's name and description
        view.findViewById<EditText>(R.id.updateName).setText(args.currentTask.name)
        view.findViewById<EditText>(R.id.updateDescription).setText(args.currentTask.description)

        // OnClickListener for the save button
        // Calls updateItem to update the current task when cicked
        view.findViewById<Button>(R.id.update_button).setOnClickListener {
            updateItem(view)
        }

        // OnClickListener for the delete button
        // Calls deleteTask to delete the current task when clicked
        view.findViewById<ImageButton>(R.id.deleteButton).setOnClickListener {
            deleteTask()
        }

        return view
    }

    private fun updateItem(view: View) {
        // Get new name and description from the EditText views
        val name = view.findViewById<EditText>(R.id.updateName).text.toString()
        val description = view.findViewById<EditText>(R.id.updateDescription).text.toString()

        if (inputCheck(name)) {
            // Create Task Object with updated values
            val updatedTask = Task(args.currentTask.id, name, description)

            // Update Current Task
            mTaskViewModel.updateTask(updatedTask)
            Toast.makeText(requireContext(), getString(R.string.task_updated_msg), Toast.LENGTH_SHORT).show()

            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), getString(R.string.task_name_req), Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name: String): Boolean {
        return name.isNotBlank()
    }

    private fun deleteTask() {
        // Create AlertDialog to confirm the deletion
        // If "Yes" current task is deleted
        // Else nothing happens
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mTaskViewModel.deleteTask(args.currentTask)
            Toast.makeText(requireContext(), "Task Deleted", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        builder.setNegativeButton("No") { _, _ -> }

        builder.setTitle("Delete ${args.currentTask.name}?")
        builder.setMessage("Are you sure you want to delete ${args.currentTask.name}?")
        builder.create().show()
    }
}