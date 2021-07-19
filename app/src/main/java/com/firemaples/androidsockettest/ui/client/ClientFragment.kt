package com.firemaples.androidsockettest.ui.client

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.firemaples.androidsockettest.R
import com.firemaples.androidsockettest.sockets.ClientSocketThread
import com.firemaples.androidsockettest.ui.BaseLogFragment
import com.firemaples.androidsockettest.utility.Constant
import com.google.android.material.textfield.TextInputLayout

class ClientFragment : BaseLogFragment(R.layout.fragment_client) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViews(view)
    }

    private fun setViews(view: View) {
        val tvLog: TextView = view.findViewById(R.id.tv_log)
        val etHost: EditText? = view.findViewById<TextInputLayout>(R.id.field_host).editText
        val etPort: EditText? = view.findViewById<TextInputLayout>(R.id.field_port).editText
        val btConnectServer: View = view.findViewById(R.id.bt_connectServer)

        etHost?.setText(Constant.serverHostToConnect)
        etPort?.setText(Constant.serverPortToConnect.toString())

        btConnectServer.setOnClickListener {
            val host = etHost?.text?.toString() ?: Constant.serverHostToConnect
            val port = etPort?.text?.toString()?.toIntOrNull() ?: Constant.serverPortToConnect

            ClientSocketThread(host, port).start()
        }

        setLogView(tvLog)
    }
}
