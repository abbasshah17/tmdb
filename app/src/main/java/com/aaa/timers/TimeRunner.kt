package com.aaa.timers

import android.util.Log
import kotlinx.coroutines.*

abstract class TimeTicker constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val delayMillis: Long = 0,
    private val tickMillis: Long = 1000,
    private val maxMillis: Long = -1,
) {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(dispatcher + job)

    private fun startCoroutineTimer(
        delayMillis: Long = 0,
        tickMillis: Long = 0,
        action: () -> Unit
    ) = scope.launch(Dispatchers.IO) {
        delay(delayMillis)
        val startTime = System.currentTimeMillis()
        if (tickMillis > 0) {
            while (maxMillis + startTime > System.currentTimeMillis()) {
                action()
                delay(tickMillis)
            }
        } else {
            action()
        }

        Log.d(TAG, "onComplete")
        scope.launch(dispatcher) {
            complete()
        }
    }

    private var timer: Job? = null

    fun start() {
        timer = startCoroutineTimer(
            delayMillis = delayMillis,
            tickMillis = tickMillis
        ) {
            Log.d(TAG, "onTick")
            scope.launch(dispatcher) {
                tick()
            }
        }
        timer?.start()
    }

    fun cancel() {
        timer?.cancel()
    }

    fun release() {
        timer?.cancel()
        scope.cancel()
    }

    fun restart() {
        cancel()
        start()
    }

    abstract fun tick()

    abstract fun complete()

    companion object {
        private const val TAG = "TimeRunner"
    }
}