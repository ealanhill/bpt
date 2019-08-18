package co.thrivemobile.bpt.account.vh

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import co.thrivemobile.bpt.account.items.AccountItem

abstract class AccountViewHolder<T : AccountItem>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: T)
}
