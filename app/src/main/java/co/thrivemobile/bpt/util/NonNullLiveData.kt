package co.thrivemobile.bpt.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class NonNullLiveData<T : Any>(value: T) : MutableLiveData<T>() {
    init {
        setValue(value)
    }

    override fun getValue(): T = super.getValue()!!

    @Suppress("RedundantOverride") // Only allow non-null value
    override fun setValue(value: T) = super.setValue(value)

    @Suppress("RedundantOverride") // Only allow non-null value
    override fun postValue(value: T) = super.postValue(value)

    fun observe(owner: LifecycleOwner, observer: (t: T) -> Unit) {
        super.observe(owner, Observer {
            it?.let(observer)
        })
    }
}