package com.much.it.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @description:
 * @project: much
 * @date: 2020/5/4
 * @author: Wenxin
 * @version: 1.0
 */
public class client {
    public static void main(String[] args) throws IOException {
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        System.out.println("Connecting to " + hostname + ":" + port);
        Socket socket = new Socket(hostname, port);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(System.in));

        String message = "";
        while ((message=bufferedReader1.readLine())!=null){
            printWriter.println(bufferedReader);
            printWriter.flush();
            if (bufferedReader.equals("exit")){
                break;
            }

            System.out.println("server" + bufferedReader.readLine());
        }
    }
}
