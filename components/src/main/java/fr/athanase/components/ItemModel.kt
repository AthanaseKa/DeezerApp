package fr.athanase.components

import androidx.annotation.LayoutRes
import androidx.databinding.BaseObservable

open class ItemModel(@LayoutRes open val layoutId: Int): BaseObservable(), Comparable<ItemModel> {
    override fun compareTo(other: ItemModel): Int {
        return 1
    }

    open fun init() {}
}