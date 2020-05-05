package com.much.it.netty1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @description:
 * @project: much
 * @date: 2020/5/4
 * @author: Wenxin
 * @version: 1.0
 */
public class TCPReactor implements Runnable {
    private final ServerSocketChannel serverSocketChannel;
    private final Selector selector;
    public TCPReactor(int port) throws IOException {
        this.selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, new Acceptor(serverSocketChannel));
    }
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("waiting for new event on port:" + serverSocketChannel.socket().getLocalSocketAddress());
            try {
                if (selector.select()==0){
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("connection");
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            if (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                Object attachment = selectionKey.attachment();
                Runnable runnable = (Runnable) attachment;
                runnable.run();
                iterator.remove();
            }
        }
    }
}
