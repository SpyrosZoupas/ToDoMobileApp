package com.leedsbeckett.todo_application.fragments.add

import com.leedsbeckett.todo_application.viewmodel.TaskViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leedsbeckett.todo_application.R
import com.leedsbeckett.todo_application.model.Task

class AddFragment : Fragment()
{

    private lateinit var  mTaskViewModel: TaskViewModel
  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        // Instantiate TaskViewModel by passing current Fragment instance to ViewModelProvider
        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        // OnClickListener for save button
        // Calls insertDataToDatabase function to save new task to database
        view.findViewById<Button>(R.id.save_button).setOnClickListener {
            insertDataToDatabase(view)
        }

        return view
    }

    // Inserts a task row into the database
    private  fun insertDataToDatabase(view: View) {

        // Get values entered by user from the view object
        val name = view.findViewById<EditText>(R.id.addName).text.toString()
        val description = view.findViewById<EditText>(R.id.addDescription).text.toString()

        // Check name is not empty
        if (inputCheck(name)) {
            //Create instance of task object using values entered by user
            val task = Task(0, name, description)
            //Add the data to the Database
            mTaskViewModel.addTask(task)
            Toast.makeText(requireContext(),getString(R.string.task_added_msg),Toast.LENGTH_LONG).show()
            //Navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), getString(R.string.task_name_req),Toast.LENGTH_LONG).show()
        }
    }

    // Returns true if name is not empty and not white-space only
    private fun inputCheck(name: String): Boolean {
        return name.isNotBlank()
    }
}