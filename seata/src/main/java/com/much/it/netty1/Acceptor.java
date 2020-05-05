package com.much.it.netty1;

import java.io.IOException;
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
    private final int cores = Runtime.getRuntime().availableProcessors();
    private final Selector[] selectors = new Selector[cores];
    private int selIdx = 0;                 //avaible subReactor index
    private TCPSubReactor[] r = new TCPSubReactor[cores];
    private Thread[] t = new Thread[cores];

    public Acceptor(ServerSocketChannel serverSocketChannel) throws IOException {
        this.serverSocketChannel = serverSocketChannel;
        for (int i = 0; i < cores; i++) {
            selectors[i] = Selector.open();
            r[i] = new TCPSubReactor(selectors[i],serverSocketChannel,i);
            t[i] = new Thread(r[i]);
            t[i].start();
        }
    }

    @Override
    public void run() {
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();     //接受请求
            System.out.println(socketChannel.socket().getRemoteSocketAddress().toString() + " is connection");
            if (socketChannel!=null){
                serverSocketChannel.configureBlocking(false);               //设置为非阻塞
                r[selIdx].setRestart(true);                                 //暂停线程
                selectors[selIdx].wakeup();                                 //使一个阻塞selector操作返回
                SelectionKey selectionKey = socketChannel.register(selectors[selIdx], SelectionKey.OP_READ);
                selectors[selIdx].wakeup();                                 //重启线程
                r[selIdx].setRestart(false);                                //给定key一个附加的tcphandler对象
                selectionKey.attach(new TCPHandler(selectionKey,socketChannel));
                if (++selIdx == selectors.length){
                    selIdx = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
