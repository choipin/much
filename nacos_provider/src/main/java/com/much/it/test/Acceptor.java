package com.much.it.test;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @description:
 * @project: much
 * @date: 2020/5/4
 * @author: Wenxin
 * @version: 1.0
 */
public class Acceptor implements Runnable{

    private final ServerSocketChannel serverSocketChannel;
    private final int cores = Runtime.getRuntime().availableProcessors();
    private Selector[] selectors = new Selector[cores];
    private TCPSubReactor[] tcpSubReactors = new TCPSubReactor[cores];
    private Thread[] threads = new Thread[cores];

    public Acceptor(ServerSocketChannel serverSocketChannel) throws IOException {
        this.serverSocketChannel = serverSocketChannel;
        for (int i = 0; i < cores; i++) {
            selectors[i] = Selector.open();
            threads[i] = new TCPSubReactor(selectors[i],serverSocketChannel,i);
            threads[i] = new Thread(threads[i]);
            threads[i].start();
        }
    }


    @Override
    public void run() {

    }
}
