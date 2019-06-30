package co.thrivemobile.bpt.entry_form

import android.graphics.Point
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import co.thrivemobile.bpt.databinding.DialogEntryFormBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EntryFormDialogFragment : DialogFragment() {

    private val entryFormViewModel: EntryFormViewModel by viewModel()

    private lateinit var binding: DialogEntryFormBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogEntryFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = entryFormViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        entryFormViewModel.errorLiveData.observe(viewLifecycleOwner, Observer { event ->
            event?.let { handleValidationEvent(it) }
        })
        entryFormViewModel.saveLivedata.observe(viewLifecycleOwner, Observer { close(it) })
        entryFormViewModel.cancelLiveEvent.observe(viewLifecycleOwner, Observer { close(it) })

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        dialog.window?.apply {
            val size = Point()
            val display = windowManager.defaultDisplay
            display.getSize(size)
            val width = size.x * .9
            setLayout(width.toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
            setGravity(Gravity.CENTER)
        }

        super.onResume()
    }

    private fun close(shouldClose: Boolean) {
        if (shouldClose) {
            dismiss()
        }
    }

    private fun handleValidationEvent(event: EntryFormValidationEvent) {
        binding.apply {
            energyEntryLayout.error = getError(event.energyErrorRes)
            focusEntryLayout.error = getError(event.focusErrorRes)
            motivationEntryLayout.error = getError(event.motivationErrorRes)
        }
    }

    private fun getError(@StringRes stringId: Int?) = stringId?.let { getString(it) }
}
