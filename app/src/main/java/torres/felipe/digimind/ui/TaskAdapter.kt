package torres.felipe.digimind.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import torres.felipe.digimind.R
import torres.felipe.digimind.ui.home.HomeFragment

class TaskAdapter: BaseAdapter {
    lateinit var context: Context
    var tasks: ArrayList<Task> = ArrayList<Task>()

    constructor(context: Context, tasks: ArrayList<Task>){
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

        vista.setOnClickListener{
            eliminar(task)
        }
        return vista
    }

    fun eliminar(task: Task){

        val alertDialog: AlertDialog? = context?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(R.string.ok_button,
                DialogInterface.OnClickListener { dialog, id ->
                    // User clicked Ok button
                    HomeFragment.tasks.remove(task)
                    guardar_json()
                    HomeFragment.adapter.notifyDataSetChanged()
                    Toast.makeText(context,R.string.msg_deleted, Toast.LENGTH_SHORT).show()
                })
            setNegativeButton(R.string.cancel_button,
                DialogInterface.OnClickListener{ dialog, id ->
                    // User cancelled the dialog
                })
            }
            builder?.setMessage(R.string.msg)
                .setTitle(R.string.title)
            builder.create()
        }
        alertDialog?.show()
    }

    fun guardar_json(){
        val preferencias = context?.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val editor = preferencias?.edit()

        val gson = Gson()

        var json = gson.toJson(HomeFragment.tasks)

        editor?.putString("tareas",json)
        editor?.apply()

    }
}