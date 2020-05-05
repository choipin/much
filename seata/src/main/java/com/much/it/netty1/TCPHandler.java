package com.much.it.netty1;

import org.apache.commons.lang3.StringUtils;

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
 * @date: 2020/5/4
 * @author: Wenxin
 * @version: 1.0
 */
public class TCPHandler implements Runnable {
    private static final int THREAD_COUNTING = 10;
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    private final SelectionKey selectionKey;
    private final SocketChannel socketChannel;

    public TCPHandler(SelectionKey selectionKey, SocketChannel socketChannel) {
        this.selectionKey = selectionKey;
        this.socketChannel = socketChannel;
        pool.setMaximumPoolSize(32);
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        ByteBuffer buf = ByteBuffer.wrap(buffer);
        int read = 0;
        String message =null;
        try {
            read = socketChannel.read(buf);
            message = new String(buffer);
            if (read == -1) {
                System.out.println("[warning] A client has been closed");
                selectionKey.cancel();
                socketChannel.close();
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (StringUtils.isAllBlank(message)) {
            //todo process(message);
            //pool.execute(new WorkerThread(message));
            System.out.println(socketChannel.socket().getRemoteSocketAddress().toString() + ">>>" + message);

            String result = "server return message";
            ByteBuffer buffers = ByteBuffer.wrap(result.getBytes());
            while (buffers.hasRemaining()) {
                try {
                    socketChannel.write(buffers);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
