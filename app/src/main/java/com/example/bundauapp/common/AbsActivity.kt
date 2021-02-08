package com.example.bundauapp.common

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bundauapp.common.ktx.createViewModelLazy
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class AbsActivity<VM : ViewModel, VB : ViewDataBinding>(val clazz: KClass<VM>) :
    DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract val layoutRes: Int

    lateinit var binding: VB

    /**
     * Lấy view model của activity.
     * */
    val viewModel: VM
        inline get() {
            return createViewModelLazy(clazz, { viewModelStore }, { viewModelFactory }).value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        lifecycleScope.launch(Dispatchers.Main) {
            initLayout()
            initActionBar()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    abstract suspend fun initLayout()

    protected abstract suspend fun initActionBar()
}