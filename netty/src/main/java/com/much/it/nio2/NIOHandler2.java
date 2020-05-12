package com.much.it.nio2;

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
public class NIOHandler2 implements Runnable{
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(10,10,10, TimeUnit.SECONDS,new LinkedBlockingQueue<>());

    private final SelectionKey selectionKey;
    private final SocketChannel socketChannel;

    public NIOHandler2(SelectionKey selectionKey, SocketChannel socketChannel) {
        this.selectionKey = selectionKey;
        this.socketChannel = socketChannel;
        pool.setMaximumPoolSize(32);
    }

    @Override
    public void run() {
        try {
            byte[] arr = new byte[1024];
            ByteBuffer buffer = ByteBuffer.wrap(arr);
            int read = socketChannel.read(buffer);
            if (read == -1){
                System.out.println("[Waiting a client has bean closed]");
                selectionKey.cancel();
                socketChannel.close();
                return;
            }
            String message = new String(arr);
            if (message!=null &&!message .equals(" ")) {
                pool.execute(new WorkerThread2(message));
                System.out.println(socketChannel.socket().getRemoteSocketAddress().toString() + " >>>> " + message);
            }

        } catch (IOException e) {
            try {
                System.out.println("[Waining a client has been closed]");
                socketChannel.close();
                selectionKey.cancel();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    class WorkerThread2 implements Runnable{
        private String message;
        public WorkerThread2(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            String returnstr =  "returnBuf your message has sent to" +socketChannel.socket().getRemoteSocketAddress().toString();
            ByteBuffer returnbuf = ByteBuffer.wrap(returnstr.getBytes());
            while (returnbuf.hasRemaining()) {
                try {
                    socketChannel.write(returnbuf);
                } catch (IOException e) {
                    try {
                        socketChannel.close();
                        selectionKey.cancel();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }
}
