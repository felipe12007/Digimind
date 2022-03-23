package torres.felipe.digimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import torres.felipe.digimind.databinding.FragmentHomeBinding
import torres.felipe.digimind.ui.Task
import torres.felipe.digimind.ui.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null


    companion object{
        var tasks: ArrayList<Task> = ArrayList<Task>()
        var first = true
        lateinit var adapter: TaskAdapter
    }
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

        cargar_tareas()

        val gridView: GridView = binding.gridview

        adapter = TaskAdapter(root.context, tasks)

        gridView.adapter = adapter

        return root
    }


    fun cargar_tareas(){
        val preferencias = context?.getSharedPreferences("preferencias", Context.MODE_PRIVATE)

        val gson = Gson()

        var json = preferencias?.getString("tareas",null)

        val type = object: TypeToken<ArrayList<Task?>?>(){}.type

        if (json == null){
            tasks = ArrayList<Task>()
        }else{
            tasks = gson.fromJson(json, type)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}