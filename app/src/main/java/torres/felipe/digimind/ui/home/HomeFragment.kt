package torres.felipe.digimind.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import torres.felipe.digimind.databinding.FragmentHomeBinding
import torres.felipe.digimind.ui.Task
import torres.felipe.digimind.ui.TaskAdapter

class HomeFragment : Fragment() {

    var tasks: ArrayList<Task> = ArrayList<Task>()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        fill_tasks()

        val gridView: GridView = binding.gridView

        val adapter = TaskAdapter(root.context, tasks)

        gridView.adapter = adapter

        return root
    }

    fun fill_tasks(){
        tasks.add(Task("tarea 1", "lunes","15:00"))
        tasks.add(Task("tarea 2", "jueves","15:00"))
        tasks.add(Task("tarea 3", "lunes","15:00"))
        tasks.add(Task("tarea 4", "lunes","15:00"))
        tasks.add(Task("tarea 5", "lunes","15:00"))
        tasks.add(Task("tarea 6", "lunes","15:00"))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}