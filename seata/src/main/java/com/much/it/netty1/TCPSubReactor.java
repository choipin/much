package com.much.it.netty1;

import java.io.IOException;
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
public class TCPSubReactor implements Runnable{
    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;
    private final int i;
    private boolean restart;
    public TCPSubReactor(Selector selector, ServerSocketChannel serverSocketChannel, int i) {
        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
        this.i = i;
    }
    @Override
    public void run() {
        while (!Thread.interrupted() && ! restart){
            System.out.println("waiting for restart");
            try {
                if (selector.select()==0){
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                Runnable runnable = (Runnable) selectionKey;
                runnable.run();
                iterator.remove();
            }
        }
    }

    public void setRestart(boolean b) {
        this.restart = b;
    }
}
