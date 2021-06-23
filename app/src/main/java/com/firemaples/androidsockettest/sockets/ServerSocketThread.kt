package com.firemaples.androidsockettest.sockets

import com.firemaples.androidsockettest.utility.Logger
import java.io.IOException
import java.io.PrintStream
import java.net.ServerSocket

class ServerSocketThread(
    private val port: Int
) : Thread("thread-server-socket") {
    private val logger: Logger = Logger(ServerSocketThread::class)

    override fun run() {
        val listener: ServerSocket
        try {
            listener = ServerSocket(port)
            logger.debug("Server listens on port: $port")
            while (true) {
                val socket = listener.accept()
                logger.debug("Accepted socket from address: ${socket.remoteSocketAddress}")
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
            }
        } catch (e: IOException) {
            logger.warn(e = e)
        }
    }
}
