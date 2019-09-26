package hu.naturlecso.spacexrockets.ui.launches

import androidx.navigation.fragment.navArgs
import hu.naturlecso.spacexrockets.R
import hu.naturlecso.spacexrockets.common.presentation.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchesFragment : BaseFragment<LaunchesViewModel>() {
    override val layoutRes: Int = R.layout.fragment_launches
    override val viewModel: LaunchesViewModel by viewModel()
    private val args: LaunchesFragmentArgs by navArgs()

    override fun afterViews() {
        viewModel.setRocketId(args.rocketId)
    }
}
