package com.example.bundauapp.presentation.table

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.bundauapp.R
import com.example.bundauapp.common.AbsActivity
import com.example.bundauapp.databinding.TableActBinding
import com.example.bundauapp.presentation.table.adapter.TableAdapter
import com.example.bundauapp.presentation.table.adapter.TableCallback
import com.example.bundauapp.widget.MyGridLayoutManager
import kotlinx.coroutines.launch

class TableAct : AbsActivity<TableViewModel, TableActBinding>(TableViewModel::class) {

    private lateinit var adapter: TableAdapter

    override val layoutRes: Int
        get() = R.layout.table_act

    override suspend fun initLayout() {
        initView()
        initAction()
        initObserver()
    }

    private fun initView() {
        binding.apply {
            tableRec.layoutManager = MyGridLayoutManager(this@TableAct, 3)
            adapter = TableAdapter(object : TableCallback {
                override fun deleteTable(tableId: String) {
                    lifecycleScope.launch {
                        viewModel.deleteTable(tableId)
                    }
                }
            })
            tableRec.adapter = adapter
        }
    }

    override suspend fun initActionBar() {
        setSupportActionBar(binding.toolBar)
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.apply {
                getListTable()?.observe(this@TableAct, Observer {
                    it?.apply {
                        countTable = size
                        adapter.updateData(this)
                        forEach {
                            Log.i("==================","TableEntity: $it")
                        }
                    }
                })
            }
        }
    }

    private fun initAction() {
        binding.apply {
            addTableBt.setOnClickListener { handelAddTable() }
        }
    }

    private fun handelAddTable() {
        lifecycleScope.launch {
            viewModel.addTable()
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, TableAct::class.java)
        }
    }
}