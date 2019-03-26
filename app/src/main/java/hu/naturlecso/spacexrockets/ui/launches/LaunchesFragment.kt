package hu.naturlecso.spacexrockets.ui.launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import hu.naturlecso.spacexrockets.R
import hu.naturlecso.spacexrockets.databinding.FragmentLaunchesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchesFragment : Fragment() {

    private val viewModel: LaunchesViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentLaunchesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_launches, container, false)

        binding.vm = viewModel

        return binding.root
    }
}
