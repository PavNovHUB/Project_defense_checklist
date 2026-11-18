package ru.edu.project

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter
    private val taskList = InitialTasks.get().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        taskAdapter = TaskAdapter(taskList) {
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = taskAdapter

        btnAdd.setOnClickListener {
            taskList.add(Task("", false))
            taskAdapter.notifyItemInserted(taskList.size - 1)
            recyclerView.scrollToPosition(taskList.size - 1)
        }

        if (taskList.isEmpty()) {
            taskList.add(Task("", false))
            taskAdapter.notifyItemInserted(0)
        }
    }
}
