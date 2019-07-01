package fr.athanase.components.statemachine.statefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.igraal.lib.componentsbase.error.ErrorViewAction
import com.igraal.lib.componentsbase.error.ErrorViewItem
import com.igraal.lib.componentsbase.error.ErrorViewType
import fr.athanase.components.databinding.FragmentStateErrorBinding

const val ERROR_TYPE = "errorType"

class ErrorStateFragment: Fragment() {

    private lateinit var binding: FragmentStateErrorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStateErrorBinding.inflate(inflater, container, false)
        binding.errorView.bindData(
            ErrorViewItem(
                ErrorViewType.GenericError().item, ErrorViewAction {
            Toast.makeText(context, "RETRY", Toast.LENGTH_SHORT).show()
        })
        )
        return binding.root
    }
}