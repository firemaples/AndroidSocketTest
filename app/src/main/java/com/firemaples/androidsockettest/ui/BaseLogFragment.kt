package com.firemaples.androidsockettest.ui

import android.widget.TextView
import androidx.fragment.app.Fragment
import com.firemaples.androidsockettest.utility.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

open class BaseLogFragment(layoutId: Int) : Fragment(layoutId) {

    private val dateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.US)

    protected fun setLogView(textView: TextView) {
        Logger.callback = {
            CoroutineScope(Dispatchers.Main).launch {
                val text =
                    "${dateFormat.format(System.currentTimeMillis())}\n$it\n\n${textView.text}"
                textView.text = text
            }
        }
    }
}