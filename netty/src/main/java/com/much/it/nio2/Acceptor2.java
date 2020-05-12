package com.much.it.nio2;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.stream.IntStream;

/**
 * @description:
 * @project: much
 * @date: 2020/5/11
 * @author: Wenxin
 * @version: 1.0
 */
public class Acceptor2 implements Runnable{
    private ServerSocketChannel serverSocketChannel;
    int cores = Runtime.getRuntime().availableProcessors();
    private Selector[] selectors= new Selector[cores];      //创建枋心数个slecto给subReactor
    private int selIdx;             //当前可使用的subReactor索引
    private NIOSubReactor2[] r = new NIOSubReactor2[cores];
    private Thread[] t = new Thread[cores];

    public Acceptor2(ServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
        IntStream.range(0,cores).boxed().forEach(integer -> {
            try {
                selectors[integer] = Selector.open();
                r[integer] = new NIOSubReactor2(selectors[integer],serverSocketChannel,integer);
                t[integer] = new Thread(r[integer]);
                t[integer] .start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void run() {
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println(socketChannel.socket().getRemoteSocketAddress().toString() + ">>> is connected");
            if (socketChannel!=null) {
                socketChannel.configureBlocking(false);
                r[selIdx].setRestart(true);
                selectors[selIdx].wakeup();
                SelectionKey selectionKey = socketChannel.register(selectors[selIdx], SelectionKey.OP_READ);
                r[selIdx].setRestart(false);
                selectionKey.attach(new NIOHandler2(selectionKey,socketChannel));
                if (++selIdx == selectors.length){
                    selIdx = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
