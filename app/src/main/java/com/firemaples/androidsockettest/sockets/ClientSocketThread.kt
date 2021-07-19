package com.firemaples.androidsockettest.sockets

import com.firemaples.androidsockettest.utility.Logger
import java.io.IOException
import java.io.PrintStream
import java.net.Socket

class ClientSocketThread(
    private val host: String,
    private val port: Int
) : Thread("thread-server-socket") {
    private val logger: Logger = Logger(ClientSocketThread::class)

    override fun run() {
        try {
            logger.debug("Connect to server: $host:$port")
            val socket = Socket(host, port)
            logger.debug("Connected server: ${socket.remoteSocketAddress}")
            
            val inStream = socket.getInputStream().bufferedReader()
            val outStream = PrintStream(socket.getOutputStream())

            var line: String?
            while (true) {
                line = inStream.readLine() ?: break
                logger.debug("Received message: $line")

                val msgToSend = "I received your message: $line"
                logger.debug("Response message: $msgToSend")
                outStream.println(msgToSend)
            }
        } catch (e: IOException) {
            logger.warn(e = e)
        }
    }
}
