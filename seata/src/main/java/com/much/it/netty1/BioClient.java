package com.much.it.netty1;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @description:
 * @project: much
 * @date: 2020/5/4
 * @author: Wenxin
 * @version: 1.0
 */
public class BioClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 1333);

            new Thread(){
                @Override
                public void run() {
                    while (true){
                        try {
                            byte[] buffer = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            int read = inputStream.read(buffer);
                            if (read>0){
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
                }
            }.start();
            Scanner scanner = new Scanner(System.in);
            while (true){
                String message = scanner.nextLine();
                socket.getOutputStream().write(message.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
