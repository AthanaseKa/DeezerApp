package fr.athanase.components

import androidx.annotation.LayoutRes
import androidx.databinding.BaseObservable

abstract class ItemBinding(
    @LayoutRes open val layoutId: Int
): BaseObservable(), Comparable<ItemBinding> {
    override fun compareTo(other: ItemBinding): Int {
        return 1
    }
    abstract val data: ItemBindingData
    abstract val actions: ItemBindingActions
}

open class ItemBindingData: BaseObservable()
open class ItemBindingActions