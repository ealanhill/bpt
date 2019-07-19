package co.thrivemobile.bpt.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.thrivemobile.bpt.databinding.FragmentInfoBinding
import org.koin.android.ext.android.inject

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private lateinit var infoAdapter: InfoAdapter
    private val infoViewModel: InfoViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        infoAdapter = InfoAdapter(viewLifecycleOwner)

        binding = FragmentInfoBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            initializeRecyclerView(infoList)
        }

        infoViewModel.infoItemsData.observe(viewLifecycleOwner) {
            infoAdapter.submitList(it)
        }

        return binding.root
    }

    private fun initializeRecyclerView(infoList: RecyclerView) {
        infoList.apply {
            adapter = infoAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}
