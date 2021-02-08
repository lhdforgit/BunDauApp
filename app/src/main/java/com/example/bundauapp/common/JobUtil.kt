/*
 * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *
 * https://help.hahalolo.com/commercial_terms/
 */

package com.example.bundauapp.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import java.util.*


object JobUtil {

    /**
     * Run 1 job and waiting to finish
     * */
    inline fun <reified T : CoroutineDispatcher> doJob(
        dispatcher: T,
        noinline doF: suspend CoroutineScope.() -> Unit
    ) {
        val scope = CoroutineScope(dispatcher)
        val job = scope.launch {
            doF()
        }
        job.invokeOnCompletion {
            if (!job.isCancelled) {
                job.cancel()
            }
            scope.cancel()
        }
    }


    private var listGlobalJobAutoCancel = Collections.synchronizedList(
        mutableListOf<Pair<String, Job>>()
    )

    internal fun <T : CoroutineDispatcher> globalJobAutoCancel(
        dispatcher: T,
        tag: String,
        delayTime: Long = 0L,
        doF: suspend CoroutineScope.() -> Unit
    ) {
        val oldList = listGlobalJobAutoCancel
        var jobNew: Job? = null
        val listTag = listGlobalJobAutoCancel.filter { it.first == tag }
        synchronized(listTag){
            if (listTag.isEmpty()) {
                jobNew = CoroutineScope(dispatcher).launch(dispatcher) {
                    jobNew?.let {
                        oldList.add(tag to it)
                        listGlobalJobAutoCancel = oldList
                    }
                    delay(delayTime)
                    doF(this)
                    cancel("Complete now cancel")
                }
            } else {
                listTag.forEach {
                    val job = it.second
                    job.cancel("Cancel")
                    oldList.remove(it)
                }

                listTag.lastOrNull()?.apply {
                    second.invokeOnCompletion {
                        jobNew = CoroutineScope(dispatcher).launch {
                            jobNew?.let {
                                oldList.add(tag to it)
                                listGlobalJobAutoCancel = oldList
                            }
                            delay(delayTime)
                            doF(this)
                        }
                        jobNew?.invokeOnCompletion {
                            jobNew?.cancel()
                            oldList.filter { it.first == tag }.forEach {
                                oldList.remove(it)
                            }
                            listGlobalJobAutoCancel = oldList
                        }
                    }
                }
            }
        }
    }

    private var job: Job? = null
    fun <T : CoroutineDispatcher> doJobNotCancelable(
        dispatcher: T,
        lifecycleOwner: LifecycleOwner,
        doF: suspend CoroutineScope.() -> Unit
    ) {

        if (job?.isActive == false || job == null) {
            job = lifecycleOwner.lifecycleScope.launch {
                withContext(dispatcher) {
                    doF.invoke(this)
                }
            }
            job?.invokeOnCompletion {
                job?.cancel()
            }
        }
    }

    fun <T : CoroutineDispatcher> doJobNotCancelable(
        dispatcher: T,
        doF: suspend CoroutineScope.() -> Unit
    ) {
        if (job?.isActive == false || job == null) {
            job = CoroutineScope(dispatcher).launch {
                withContext(dispatcher) {
                    doF.invoke(this)
                }
            }
            job?.invokeOnCompletion {
                job?.cancel()
            }
        }
    }
}

/**
 * --------------------------------------------------------------------------------------------------
 * */
fun runJobMain(doF: suspend CoroutineScope.() -> Unit) = runJob(Dispatchers.Main) { doF() }

fun globalJobCancelIfDuplecate(
    dispatcher: CoroutineDispatcher = Dispatchers.Main,
    tag: String,
    delayTime: Long = 0L,
    doF: suspend CoroutineScope.() -> Unit
) = JobUtil.globalJobAutoCancel(dispatcher, tag, delayTime, doF)

infix fun CoroutineScope.runMain(value: suspend CoroutineScope.() -> Unit) {
    launch {
        runJob(Dispatchers.Main) { value.invoke(this) }
    }
}

infix fun CoroutineScope.runDefault(value: suspend CoroutineScope.() -> Unit) {
    launch {
        runJob(Dispatchers.Default) { value.invoke(this) }
    }
}

infix fun CoroutineScope.shiftMain(doF: suspend CoroutineScope.() -> Unit) =
    runJob(Dispatchers.Main) { doF() }

inline fun <reified T : CoroutineDispatcher> runNotCancelable(
    value: T,
    lifecycleOwner: LifecycleOwner,
    noinline doF: suspend CoroutineScope.() -> Unit
) = JobUtil.doJobNotCancelable(value, lifecycleOwner, doF)

inline fun <reified T : CoroutineDispatcher> LifecycleOwner.runNotCancelable(
    value: T,
    noinline doF: suspend CoroutineScope.() -> Unit
) = JobUtil.doJobNotCancelable(value, this, doF)

fun LifecycleOwner.runNotCancelableMain(doF: suspend CoroutineScope.() -> Unit) =
    JobUtil.doJobNotCancelable(Dispatchers.Main, this, doF)

inline fun <reified T : CoroutineDispatcher> runJob(
    value: T,
    noinline doF: suspend CoroutineScope.() -> Unit
) = JobUtil.doJob(value, doF)