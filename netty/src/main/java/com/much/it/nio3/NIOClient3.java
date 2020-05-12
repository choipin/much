package com.much.it.nio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @description:
 * @project: much
 * @date: 2020/5/12
 * @author: Wenxin
 * @version: 1.0
 */
public class NIOClient3 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 1333);
            new Thread(()->{
                try {
                    byte[] read = new byte[64];
                    int read1 = socket.getInputStream().read(read);
                    if (read1>0) {
                        System.out.println(new String(read));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        socket.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }).start();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                String line = bufferedReader.readLine();
                if (line!=null && !line.equals("")) {
                    socket.getOutputStream().write(line.getBytes());
                    byte[] read = new byte[64];
                    int read1 = socket.getInputStream().read(read);
                    if (read1>0) {
                        System.out.println(new String(read));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
