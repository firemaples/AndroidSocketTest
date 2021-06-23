package com.firemaples.androidsockettest.utility

import android.util.Log
import kotlin.reflect.KClass

class Logger(clazz: KClass<*>) {
    private val tag = clazz.simpleName

    companion object {
        var callback: ((String) -> Unit)? = null
    }

    fun debug(msg: String? = null, e: Throwable? = null) {
        Log.d(tag, msg, e)
        doCallback("D", msg, e)
    }

    fun info(msg: String? = null, e: Throwable? = null) {
        Log.i(tag, msg, e)
        doCallback("I", msg, e)
    }

    fun warn(msg: String? = null, e: Throwable? = null) {
        Log.w(tag, msg, e)
        doCallback("W", msg, e)
    }

    fun error(msg: String? = null, e: Throwable? = null) {
        Log.e(tag, msg, e)
        doCallback("E", msg, e)
    }

    private fun doCallback(level: String, msg: String? = null, e: Throwable? = null) {
        callback?.invoke("$level/[$tag] $msg ${Log.getStackTraceString(e)}")
    }
}