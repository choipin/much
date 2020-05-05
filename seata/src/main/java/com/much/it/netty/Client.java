package com.much.it.netty;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @description:
 * @project: much
 * @date: 2020/5/4
 * @author: Wenxin
 * @version: 1.0
 */
public class Client {
    public static void main(String[] args) throws IOException {
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        System.out.println("Connecting to " + hostname + ":" + port);
        Socket socket = new Socket(hostname, port);
        OutputStream outputStream = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        byte[] buffer = new byte[1024];
        while (message != null) {
            outputStream.write(message.getBytes());
            outputStream.flush();
            if (message.equals("exit")) {
                break;
            }
            socket.getInputStream().read(buffer);
            System.out.println("server" + new String(buffer));
        }
    }
}
