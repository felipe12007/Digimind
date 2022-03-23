package torres.felipe.digimind.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import torres.felipe.digimind.R

class TaskAdapter: BaseAdapter {
    lateinit var context: Context
    var tasks: ArrayList<Task> = ArrayList<Task>()

    constructor(context: Context, task: ArrayList<Task>){
        this.context = context
        this.tasks = tasks
    }

    override fun getCount(): Int {
        return tasks.size
    }

    override fun getItem(p0: Int): Any {
        return  tasks[p0]

    }

    override fun getItemId(p0: Int): Long {
       return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var inflador = LayoutInflater.from(context)
        var vista = inflador.inflate(R.layout.task_view,null)

        var task = tasks[p0]
        var tv_title: TextView = vista.findViewById(R.id.tv_title)
        var tv_day: TextView = vista.findViewById(R.id.tv_days)
        var tv_time: TextView = vista.findViewById(R.id.tv_time)

        tv_title.setText(task.title)
        tv_day.setText(task.day)
        tv_time.setText(task.time)

        return vista
    }


}