package com.much.it.nio2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @description:
 * @project: much
 * @date: 2020/5/11
 * @author: Wenxin
 * @version: 1.0
 */
public class NIOReactor2 implements Runnable{
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    public NIOReactor2(int port) {
        try {
            this.serverSocketChannel = ServerSocketChannel.open();
            this.selector = Selector.open();
            this.serverSocketChannel.configureBlocking(false);
            this.serverSocketChannel.bind(new InetSocketAddress(port));
            this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT,new Acceptor2(serverSocketChannel));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("mainReactor waiting for new event on port" + serverSocketChannel.socket().getLocalPort() + "...");

                selector.select();
                System.out.println("event has connection");
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    Runnable runnable = (Runnable) iterator.next().attachment();
                    if (runnable!=null) {
                        runnable.run();
                    }
                    iterator.remove();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
