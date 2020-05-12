package com.much.it.nio2;

import java.io.IOException;
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
public class NIOSubReactor2 implements Runnable{
    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;
    private final Integer integer;
    boolean restart = false;
    public NIOSubReactor2(Selector selector, ServerSocketChannel serverSocketChannel, Integer integer) {

        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
        this.integer = integer;
    }

    public void setRestart(boolean restart) {
        this.restart = restart;
    }

    @Override
    public void run() {
        while (true){
            System.out.println("waiting for restart");

            while (!Thread.interrupted() && restart){
                try {
                    if (selector.select() == 0){
                        continue;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("----------------");
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    Runnable runnable = (Runnable) iterator.next().attachment();
                    if (runnable!=null) {
                        runnable.run();
                    }
                    iterator.remove();
                }
            }
        }
    }
}
