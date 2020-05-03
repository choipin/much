package com.much.it.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * @description:
 * @project: much
 * @date: 2020/5/3
 * @author: Wenxin
 * @version: 1.0
 */
public class TCPReactor {
    ServerSocketChannel serverSocketChannel;
    Selector selector;

    public TCPReactor(int port) {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(9090));
            selector = Selector.open();
            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            //selectionKey.attach(new Acceptor(selector,selectionKey));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("waiting for new event on port:" + serverSocketChannel.socket().getLocalPort());
            try {
                //not socket connection will blocking here,
                if (selector.select()==0){
                    continue;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            // has socket connection
            System.out.println("has socket connection");
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //iterator all event and excutor
            while (selectionKeys.iterator().hasNext()) {
                SelectionKey next = selectionKeys.iterator().next();
                Runnable runnable = (Runnable) next.attachment();
                if (runnable!=null){
                    runnable.run();
                }
                selectionKeys.iterator().remove();
            }
        }
    }
}
