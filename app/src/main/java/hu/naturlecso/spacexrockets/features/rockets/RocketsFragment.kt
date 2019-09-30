package hu.naturlecso.spacexrockets.features.rockets

import hu.naturlecso.spacexrockets.R
import hu.naturlecso.spacexrockets.common.presentation.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class RocketsFragment : BaseFragment<RocketsViewModel>() {
    override val layoutRes: Int = R.layout.fragment_rockets
    override val viewModel: RocketsViewModel by viewModel()

    override fun afterViews() {
        viewModel.navigateToWelcomeCommand.execute()
    }
}
