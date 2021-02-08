package com.example.bundauapp.presentation.table.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bundauapp.R
import com.example.bundauapp.data.room.entity.TableEntity
import com.example.bundauapp.databinding.TableItemBinding

class TableAdapter(private val callback: TableCallback) : RecyclerView.Adapter<TableViewHolder>() {

    private var listTable = mutableListOf<TableEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        return TableViewHolder.createHolder(parent, callback)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.bind(listTable[position], listTable.size)
    }

    override fun getItemCount(): Int {
        return listTable.size
    }

    fun updateData(listData: List<TableEntity>) {
        val diffCallback = TableDiff(listTable, listData.toMutableList())
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listTable.clear()
        this.listTable.addAll(listData)
        //diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

}


class TableDiff(
    private var oldList: MutableList<TableEntity>,
    private var newList: MutableList<TableEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return TextUtils.equals(oldList[oldItemPosition].id, newList[newItemPosition].id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return TextUtils.equals(oldList[oldItemPosition].id, newList[newItemPosition].id)
                && TextUtils.equals(oldList[oldItemPosition].name, newList[newItemPosition].name)
                && oldList[oldItemPosition].number == newList[newItemPosition].number
    }

}

class TableViewHolder(private val binding: TableItemBinding, private val callback: TableCallback) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(table: TableEntity?, size: Int) {
        table?.apply {
            binding.nameTv.text = name ?: ""
            binding.deleteBt.setOnClickListener {
                callback.deleteTable(id)
            }
            binding.deleteBt.isEnabled = size == number
            binding.deleteBt.alpha = if (size == number) 1f else 0.5f
        }
    }

    companion object {
        fun createHolder(viewGroup: ViewGroup, callback: TableCallback): TableViewHolder {
            val inflater = LayoutInflater.from(viewGroup.context)
            val binding = DataBindingUtil.inflate<TableItemBinding>(
                inflater,
                R.layout.table_item,
                viewGroup,
                false
            )
            return TableViewHolder(binding, callback)
        }
    }
}

interface TableCallback {
    fun deleteTable(tableId: String)
}