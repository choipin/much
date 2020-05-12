package com.much.it.nio;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @description:
 * @project: much
 * @date: 2020/5/10
 * @author: Wenxin
 * @version: 1.0
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 1333);
            new Thread(()->{
                while (true){
                    try {
                        byte[] buffer = new byte[1024];
                        int read = socket.getInputStream().read(buffer);
                        if (read>0) {
                            System.out.println(new String(buffer));
                        }
                    } catch (IOException e) {
                        try {
                            socket.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            }).start();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                String message = bufferedReader.readLine();
                socket.getOutputStream().write(message.getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
