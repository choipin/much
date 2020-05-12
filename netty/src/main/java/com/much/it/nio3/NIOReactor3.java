package com.much.it.nio3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @description:
 * @project: much
 * @date: 2020/5/12
 * @author: Wenxin
 * @version: 1.0
 */
public class NIOReactor3 implements Runnable {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public NIOReactor3(int port) {
        try {
            this.serverSocketChannel = ServerSocketChannel.open();
            this.selector = Selector.open();
            this.serverSocketChannel.configureBlocking(false);
            this.serverSocketChannel.bind(new InetSocketAddress(port));
            this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, new Acceptor3(serverSocketChannel));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    Runnable attachment = (Runnable) iterator.next().attachment();
                    if (attachment != null) {
                        attachment.run();
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                selector.close();
                serverSocketChannel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
