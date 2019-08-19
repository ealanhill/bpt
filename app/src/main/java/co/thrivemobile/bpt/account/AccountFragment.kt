package co.thrivemobile.bpt.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import co.thrivemobile.bpt.databinding.FragmentAccountBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : Fragment() {

    private var accountViewModel: AccountViewModel by viewModel()
    private lateinit var binding: FragmentAccountBinding
    private lateinit var accountAdapter: AccountAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountAdapter = AccountAdapter(viewLifecycleOwner)

        binding = FragmentAccountBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner

            accountList.apply {
                adapter = accountAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }

        accountViewModel.accountItemsLiveData.observe(
            viewLifecycleOwner,
            Observer { items ->
                accountAdapter.submitList(items)
            }
        )

        return binding.root
    }
}
