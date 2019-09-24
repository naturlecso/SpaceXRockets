package hu.naturlecso.spacexrockets.ui.launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import hu.naturlecso.spacexrockets.R
import hu.naturlecso.spacexrockets.databinding.FragmentLaunchesBinding
import kotlinx.android.synthetic.main.fragment_launches.launchList
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchesFragment : Fragment() {

    private val viewModel: LaunchesViewModel by viewModel()

    private val args: LaunchesFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentLaunchesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_launches, container, false)

        binding.vm = viewModel
        viewModel.setRocketId(args.rocketId)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchList.apply {
            addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL).apply {
                setDrawable(checkNotNull(ContextCompat.getDrawable(context, R.drawable.divider_list)))
            })
        }
    }
}
