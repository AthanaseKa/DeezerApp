package fr.athanase.components.statemachine.statefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.igraal.lib.componentsbase.error.ErrorViewAction
import com.igraal.lib.componentsbase.error.ErrorViewItem
import com.igraal.lib.componentsbase.error.ErrorViewType
import fr.athanase.components.databinding.FragmentStateNetworkErrorBinding

class NetworkErrorStateFragment : Fragment() {

    private lateinit var binding: FragmentStateNetworkErrorBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStateNetworkErrorBinding.inflate(inflater, container, false)
        binding.networkErrorView.bindData(
            ErrorViewItem(
                ErrorViewType.NetworkError().item, ErrorViewAction {
            getViewmodelOperation()
        })
        )
        return binding.root
    }

    private fun getViewmodelOperation() {
        val parent = parentFragment
        if (parent is BaseStateFragment<*>) {
            parent.viewModel.setLoading()
        }
    }
}