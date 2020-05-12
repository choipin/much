package com.much.it.nio3;

import java.io.IOException;
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
public class NIOSubReactor3 implements Runnable {
    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;
    private final Integer integer;

    public void setRestart(boolean restart) {
        this.restart = restart;
    }

    private boolean restart = false;

    public NIOSubReactor3(Selector selector, ServerSocketChannel serverSocketChannel, Integer integer) {

        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
        this.integer = integer;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted() && restart){
                System.out.println("waiting for restart");
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    Runnable runnable = (Runnable) iterator.next().attachment();
                    if (runnable!=null) {
                        runnable.run();
                    }
                }
                iterator.remove();
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
