package hu.naturlecso.spacexrockets.ui.rockets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import hu.naturlecso.spacexrockets.R
import hu.naturlecso.spacexrockets.databinding.FragmentRocketsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RocketsFragment : Fragment() {

    private val viewModel: RocketsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentRocketsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_rockets, container, false)

        binding.vm = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateToWelcomeCommand.execute()
    }
}
