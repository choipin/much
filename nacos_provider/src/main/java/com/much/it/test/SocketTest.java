package com.much.it.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @project: much
 * @date: 2020/5/3
 * @author: Wenxin
 * @version: 1.0
 */
public class SocketTest {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    private static void test3() {
        TCPReactor tcpReactor = new TCPReactor(1333);
        tcpReactor.run();
    }

    private static void test1() {
        try {
            Socket socket = new Socket("127.0.0.1", 9090);

            socket.getOutputStream().write("h".getBytes());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test2(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9090);
            while (true) {
                Socket accept = serverSocket.accept();
                System.out.println("connect one client");
                byte[] buffer = new byte[1024];
                accept.getInputStream().read(buffer);
                String message = new String(buffer, StandardCharsets.UTF_8);
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
