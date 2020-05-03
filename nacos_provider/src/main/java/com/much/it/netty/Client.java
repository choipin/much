package com.much.it.netty;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @description:
 * @project: much
 * @date: 2020/5/3
 * @author: Wenxin
 * @version: 1.0
 */
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(),8080));
        while (!socketChannel.finishConnect()){
            Thread.yield();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input");
        String message = scanner.nextLine();
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        while (buffer.hasRemaining()){
            socketChannel.write(buffer);
        }

        System.out.println("receive echoResponse from server");
        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
        while (socketChannel.isOpen() && socketChannel.read(requestBuffer)!=-1){
            if (requestBuffer.position()>0){
                break;
            }
        }
        requestBuffer.flip();
        byte[] content = new byte[requestBuffer.limit()];
        requestBuffer.get(content);
        System.out.println(new String(content));
        System.out.println(new String(requestBuffer.array()));

        scanner.close();
        socketChannel.close();
    }
}
