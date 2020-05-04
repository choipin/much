package com.much.it.netty;

import lombok.SneakyThrows;

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
public class TCPReactor implements Runnable{

    private final ServerSocketChannel serverSocketChannel;
    private final Selector selector;

    public TCPReactor(int port) throws IOException {
        this.serverSocketChannel = ServerSocketChannel.open();
        this.selector = Selector.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT,new Acceptor(selector,serverSocketChannel));

    }


    @SneakyThrows
    @Override
    public void run() {
        while (!Thread.interrupted()){
            System.out.println("waiting for new event on port:" + serverSocketChannel.socket().getLocalPort());
            if (selector.select() ==0){
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                iterator.remove();
                Runnable runnable = (Runnable) next.attachment();
                runnable.run();
            }
        }
    }


}
