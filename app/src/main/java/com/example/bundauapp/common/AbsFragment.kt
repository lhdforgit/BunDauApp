package com.example.bundauapp.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.*
import com.example.bundauapp.common.ktx.autoCleared
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class AbsFragment<VM : ViewModel, VB : ViewDataBinding>(private val classT: KClass<VM>) :
    DaggerFragment(), LifecycleOwner {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract val layoutRes: Int

    lateinit var binding: VB

    lateinit var lifecycleRegistry: LifecycleRegistry

    val viewModel: VM
        get() {
            return createViewModelLazy(classT, {
                requireActivity().viewModelStore
            }, { viewModelFactory }).value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.currentState = (Lifecycle.State.CREATED)
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.currentState = (Lifecycle.State.STARTED)
    }

    override fun onResume() {
        super.onResume()
        lifecycleRegistry.currentState = (Lifecycle.State.RESUMED)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleRegistry.currentState = (Lifecycle.State.DESTROYED)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return runCatching {
            inflater.inflate(
                layoutRes,
                container,
                false
            )
        }.getOrElse {
            throw Error("CommonNavigationFr ${it.message}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        runCatching {
            lifecycleScope.launch(Dispatchers.Main) {
                view.getNewDataBinding<VB>()?.apply {
                    binding = autoCleared<VB>().setValue(this)
                    initLayout()
                }
                if (::binding.isInitialized) {
                    binding.setLifecycleOwner { lifecycleRegistry }
                    binding.root.requestLayout()
                    binding.executePendingBindings()
                }
            }
        }.getOrElse {
            throw Error("CommonNavigationFr ${it.message}")
        }
    }

    private fun <V : ViewDataBinding> View.getNewDataBinding(): V? {
        val view = DataBindingUtil.findBinding<V>(this)
        return if (view != null) null else DataBindingUtil.bind(this)
    }

    abstract suspend fun initLayout()

}