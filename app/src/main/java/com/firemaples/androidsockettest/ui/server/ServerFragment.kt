package com.firemaples.androidsockettest.ui.server

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.firemaples.androidsockettest.R
import com.firemaples.androidsockettest.sockets.ServerSocketThread
import com.firemaples.androidsockettest.ui.BaseLogFragment
import com.firemaples.androidsockettest.utility.Constant

class ServerFragment : BaseLogFragment(R.layout.fragment_server) {
    private val viewModel: ServerViewModel by viewModels()

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
            ServerSocketThread(Constant.serverPort).start()
        }

        setLogView(tvLog)

        viewModel.prepareData(requireContext())
    }
}