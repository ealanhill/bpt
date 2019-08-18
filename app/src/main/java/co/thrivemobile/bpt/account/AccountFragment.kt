package co.thrivemobile.bpt.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.thrivemobile.bpt.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

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

        return binding.root
    }
}
