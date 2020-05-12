package com.much.it.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @description:
 * @project: much
 * @date: 2020/5/10
 * @author: Wenxin
 * @version: 1.0
 */
public class TCPReator implements Runnable{
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    public TCPReator(int prot) {
        try {
            this.serverSocketChannel = ServerSocketChannel.open();
            this.selector = Selector.open();
            this.serverSocketChannel.configureBlocking(false);
            this.serverSocketChannel.bind(new InetSocketAddress(prot));
            this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, new Acceptor(selector, serverSocketChannel));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (!Thread.interrupted()){
            try {
                if (selector.select()==0) {
                    continue;
                }
                System.out.println("has connecting now");
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    Object attachment = selectionKey.attachment();
                    //attachment if op_accept ===>Accpetor implement runnable
                    //attachment if op_read =====>tcphandler implement runnable
                    Runnable runnable = (Runnable) attachment;
                    if (runnable !=null) {
                        runnable.run();
                    }
                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
