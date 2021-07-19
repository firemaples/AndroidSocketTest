package com.firemaples.androidsockettest.ui.server

import android.content.Context
import android.net.wifi.WifiManager
import androidx.core.content.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firemaples.androidsockettest.utility.Constant
import kotlinx.coroutines.launch

class ServerViewModel : ViewModel() {

    private val _networkInfo = MutableLiveData<String>()
    val networkInfo: LiveData<String> = _networkInfo

    fun prepareData(context: Context) {
        viewModelScope.launch {
            val ip = getWifiIp(context)
            if (ip.split(".").size >= 4) {
                _networkInfo.value = "Connect the Android server on MAC by 'netcat $ip ${Constant.serverPort}'"
            } else {
                _networkInfo.value =
                    "$ip, you can still use 'adb forward tcp:${Constant.serverPort} tcp:${Constant.serverPort}' and 'netcat localhost ${Constant.serverPort}' to connect the Android server'"
            }
        }
    }

    fun getWifiIp(context: Context): String {
        return context.getSystemService<WifiManager>().let {
            when {
                it == null -> "No Wifi available"
                !it.isWifiEnabled -> "Wifi is not enabled"
                it.connectionInfo == null -> "Wifi is not connected"
                else -> {
                    val ip = it.connectionInfo.ipAddress
                    ((ip and 0xFF).toString() + "." + (ip shr 8 and 0xFF) + "." + (ip shr 16 and 0xFF) + "." + (ip shr 24 and 0xFF))
                }
            }
        }
    }
}
