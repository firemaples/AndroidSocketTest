package com.firemaples.androidsockettest.ui.server

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.firemaples.androidsockettest.R
import com.firemaples.androidsockettest.sockets.ServerSocketThread
import com.firemaples.androidsockettest.utility.Constant
import com.firemaples.androidsockettest.utility.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ServerFragment : Fragment(R.layout.fragment_server) {
    private val viewModel: ServerViewModel by viewModels()

    private val dateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.US)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews(view)
    }

    private fun setViews(view: View) {
        val tvNetworkInfo: TextView = view.findViewById(R.id.tv_networkInfo)
        val btStartServer: View = view.findViewById(R.id.bt_startServer)
        val tvLog: TextView = view.findViewById(R.id.tv_log)

        viewModel.networkInfo.observe(viewLifecycleOwner) {
            tvNetworkInfo.text = it
        }

        btStartServer.setOnClickListener {
            ServerSocketThread(Constant.port).start()
        }

        Logger.callback = {
            CoroutineScope(Dispatchers.Main).launch {
                val text = "${dateFormat.format(System.currentTimeMillis())}\n$it\n\n${tvLog.text}"
                tvLog.text = text
            }
        }

        viewModel.prepareData(requireContext())
    }
}