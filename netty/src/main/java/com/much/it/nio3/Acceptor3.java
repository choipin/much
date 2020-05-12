package com.much.it.nio3;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.stream.IntStream;

/**
 * @description:
 * @project: much
 * @date: 2020/5/12
 * @author: Wenxin
 * @version: 1.0
 */
public class Acceptor3 implements Runnable {
    int core = Runtime.getRuntime().availableProcessors();
    private ServerSocketChannel serverSocketChannel;
    private Selector[] selectors = new Selector[core];
    private NIOSubReactor3[] nioSubReactor3s = new NIOSubReactor3[core];
    private Thread[] threads = new Thread[core];
    private int index;

    public Acceptor3(ServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
        IntStream.range(0, core).boxed().forEach(integer -> {
            try {
                selectors[integer] = Selector.open();
                nioSubReactor3s[integer] = new NIOSubReactor3(selectors[integer], serverSocketChannel, integer);
                threads[integer] = new Thread(nioSubReactor3s[integer]);
                threads[integer].start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void run() {
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println(socketChannel.socket().getRemoteSocketAddress().toString() + " >>>>  is connection");
            if (socketChannel != null) {
                socketChannel.configureBlocking(false);
                nioSubReactor3s[index].setRestart(true);
                selectors[index].wakeup();
                SelectionKey selectionKey = socketChannel.register(selectors[index], SelectionKey.OP_READ);
                nioSubReactor3s[index].setRestart(false);
                selectors[index].wakeup();
                selectionKey.attach(new NIOHandler3(selectionKey,socketChannel));
                if (++index == selectors.length) {
                    index = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                selectors[index].close();
                serverSocketChannel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
