package fr.athanase.components

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class ViewHolder<Model: ItemBinding>(open val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Model) {
        binding.setVariable(BR.binding, model)
        binding.executePendingBindings()
    }
}