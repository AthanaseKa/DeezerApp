package fr.athanase.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

open class GenericAdapter: RecyclerView.Adapter<ViewHolder<ItemBinding>>()  {
    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun <T: ItemBinding> RecyclerView.bindItems(items: List<T>) {
            (adapter as GenericAdapter?)?.setItemList(items)
        }
    }

    private var itemList: MutableList<ItemBinding> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<ItemBinding> {
        val binding: ViewDataBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context), viewType,
                parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder<ItemBinding>, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(itemList[position])
    }

    fun getLayoutId(model: ItemBinding): Int {
        return model.layoutId
    }

    fun setItemList(list: List<ItemBinding>) {
        val diffCallback = DiffCallback<ItemBinding>()
        diffCallback.setLists(this.itemList, list)
        val result = DiffUtil.calculateDiff(diffCallback)
        this.itemList.clear()
        this.itemList.addAll(list)
        result.dispatchUpdatesTo(this)
    }

    fun getItemList(): List<ItemBinding> {
        return itemList
    }
}

class DiffCallback<T: Comparable<T>>: DiffUtil.Callback() {
    private var oldList: List<T> = emptyList()
    private var newList: List<T> = emptyList()

    fun setLists(oldList: List<T>, newList: List<T>) {
        this.oldList = oldList
        this.newList = newList
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition].compareTo(newList[newItemPosition]) == 0

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true
}
