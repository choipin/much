package com.much.it.nio3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @project: much
 * @date: 2020/5/12
 * @author: Wenxin
 * @version: 1.0
 */
public class NIOHandler3 implements Runnable{
    private final SelectionKey selectionKey;
    private final SocketChannel socketChannel;
    private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(10,10,10, TimeUnit.SECONDS,new LinkedBlockingQueue<>());

    public NIOHandler3(SelectionKey selectionKey, SocketChannel socketChannel) {
        this.selectionKey = selectionKey;
        this.socketChannel = socketChannel;
        pool.setMaximumPoolSize(32);
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[1024];
            ByteBuffer buffer = ByteBuffer.wrap(buf);
            int read = socketChannel.read(buffer);
            if (read == -1) {
                socketChannel.close();
                selectionKey.cancel();
                return;
            }
            String message = new String(buf);
            System.out.println(message);
            if (message!=null && message.equals("")) {
                pool.execute(()->{
                    String resp = socketChannel.socket().getRemoteSocketAddress().toString()+"  >>>>  "+message;
                    ByteBuffer wrap = ByteBuffer.wrap(resp.getBytes());
                    while (wrap.hasRemaining()) {
                        try {
                            socketChannel.write(wrap);
                        } catch (IOException e) {
                            e.printStackTrace();
                            try {
                                socketChannel.close();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    }
                });
                System.out.println(socketChannel.socket().getRemoteSocketAddress().toString() + "<<<<<<>>>>>" + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                System.out.println("waining a client has been closed");
                socketChannel.close();
                selectionKey.cancel();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
