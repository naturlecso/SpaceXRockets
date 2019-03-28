package hu.naturlecso.spacexrockets.ui.welcome

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import hu.naturlecso.spacexrockets.R
import hu.naturlecso.spacexrockets.databinding.FragmentWelcomeBinding
import kotlinx.android.synthetic.main.fragment_welcome.startButton
import org.koin.androidx.viewmodel.ext.android.viewModel



class WelcomeFragment: DialogFragment() {

    private val viewModel: WelcomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_welcome, container, false)

        binding.vm = viewModel

        isCancelable = false

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startButton.setOnClickListener { dismiss() }

        viewModel.refreshCommand.execute()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(activity, R.style.DialogTheme)
    }
}
