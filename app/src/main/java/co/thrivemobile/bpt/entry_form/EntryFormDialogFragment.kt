package co.thrivemobile.bpt.entry_form

import android.os.Bundle
import android.util.AttributeSet
import android.util.Xml
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.view.ContextThemeWrapper
import androidx.lifecycle.Observer
import co.thrivemobile.bpt.R
import co.thrivemobile.bpt.base.BaseBottomDialogFragment
import co.thrivemobile.bpt.databinding.DialogEntryFormBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import org.koin.androidx.viewmodel.ext.android.viewModel

class EntryFormDialogFragment : BaseBottomDialogFragment() {

    private val entryFormViewModel: EntryFormViewModel by viewModel()

    private lateinit var binding: DialogEntryFormBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogEntryFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = entryFormViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        entryFormViewModel.saveLivedata.observe(viewLifecycleOwner, Observer { close(it) })
        entryFormViewModel.cancelLiveEvent.observe(viewLifecycleOwner, Observer { close(it) })

        setUpChips(binding.energyChipGroup)
        setUpChips(binding.focusChipGroup)
        setUpChips(binding.motivationChipGroup)

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpChips(chipGroup: ChipGroup) {
        for (i in 1..10) {
            val chip = Chip(context).apply {
                setChipDrawable(ChipDrawable.createFromResource(context, R.xml.chip_styling))
                text = i.toString()
                id = i
                setTextAppearanceResource(R.style.ChipTextStyle)
            }
            chipGroup.addView(chip)
        }
    }

    private fun close(shouldClose: Boolean) {
        if (shouldClose) {
            dismiss()
        }
    }

    private fun getError(@StringRes stringId: Int?) = stringId?.let { getString(it) }
}
