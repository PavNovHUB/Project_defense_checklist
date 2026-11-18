package ru.edu.project

import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val tasks: MutableList<Task>,
    private val onTaskChanged: () -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
        val etTaskTitle: EditText = itemView.findViewById(R.id.etTaskTitle)
        var textWatcher: TextWatcher? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        holder.checkBox.setOnCheckedChangeListener(null)
        holder.textWatcher?.let { holder.etTaskTitle.removeTextChangedListener(it) }

        holder.checkBox.isChecked = task.isChecked
        holder.etTaskTitle.setText(task.title)

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            task.isChecked = isChecked
            onTaskChanged()
        }

        holder.textWatcher = holder.etTaskTitle.addTextChangedListener { text ->
            task.title = text.toString()
        }
    }

    override fun getItemCount(): Int = tasks.size
}
