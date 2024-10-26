package com.maskarad.ratatoskr;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client{
    public static SocketChannel connect(String ip, int port)  throws IOException{
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress(ip, port));
        return client;
    }

    public static void send(SocketChannel client, String message) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap((message + "\n").getBytes()); // Add newline
        client.write(buffer);
    }

}
