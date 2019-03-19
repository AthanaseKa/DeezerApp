package fr.athanase.components

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class ViewHolder<Model: ItemModel, Action: ItemAction>(open val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Model, action: Action) {
        binding.setVariable(BR.data, model)
        binding.setVariable(BR.action, action)
        binding.executePendingBindings()
    }
}