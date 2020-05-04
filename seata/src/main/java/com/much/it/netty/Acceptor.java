package com.much.it.netty;

import lombok.SneakyThrows;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @description:
 * @project: much
 * @date: 2020/5/4
 * @author: Wenxin
 * @version: 1.0
 */
public class Acceptor implements Runnable{

    private final ServerSocketChannel serverSocketChannel;
    private final Selector selector;

    public Acceptor(Selector selector, ServerSocketChannel serverSocketChannel) {
        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
    }

    @SneakyThrows
    @Override
    public void run() {
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println(socketChannel.socket().getRemoteSocketAddress().toString() + "==> is connecting");

        if (socketChannel !=null){
            socketChannel.configureBlocking(false);
            SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
            selectionKey.attach(new TCPHandler(selectionKey,socketChannel));
            selector.wakeup();

        }
    }
}
