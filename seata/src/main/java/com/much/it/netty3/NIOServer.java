package com.much.it.netty3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @description:
 * @project: much
 * @date: 2020/5/5
 * @author: Wenxin
 * @version: 1.0
 */
public class NIOServer {
    static Integer cores = Runtime.getRuntime().availableProcessors();
    public static WorkThread[] workThreadst = new WorkThread[cores];
    public static void main(String[] args) {
        BossThread bossThread = new BossThread(1888);
        bossThread.run();
        //创建处理数据的线程组
        IntStream.range(0, cores).boxed().forEach(integer -> new WorkThread().start());
    }
    //负责接客，处理新连接
    static class BossThread implements Runnable {
        private ServerSocketChannel serverSocketChannel;
        private Selector selector;

        BossThread(int port) {
            try {
                this.serverSocketChannel = ServerSocketChannel.open();
                this.selector = Selector.open();
                this.serverSocketChannel.configureBlocking(false);
                this.serverSocketChannel.bind(new InetSocketAddress(port));
                this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void run() {
            try {
                while (true) {
                    int select = selector.select(1000L);
                    if (select == 0) {
                        continue;
                    }
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        workThreadst[new Random().nextInt(cores)].handlerSocket(socketChannel);
                        System.out.println("有新的连接了");
                    }
                    selectionKeys.clear();
                    selector.selectNow();
                }
            }catch (Exception e){

            }

        }
    }

    //有来处理有数据的连接
    static class WorkThread extends Thread{
        private Selector selector;

        WorkThread() {
            try {
                this.selector = Selector.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //has connection, but should not data
        public void handlerSocket(SocketChannel socketChannel){
            try {
                //继续交给selector，有数据了通知我去读
                socketChannel.register(selector, SelectionKey.OP_READ);
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true){
                    //查询通知，有新连接立即返回
                    if (selector.select(1000L)==0) {
                        continue;
                    }
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isReadable()){
                            SelectableChannel selectableChannel = selectionKey.channel();
                            SocketChannel socketChannel = (SocketChannel) selectableChannel;
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            socketChannel.read(buffer);
                            System.out.println(new String(buffer.array()));
                        }
                    }
                }
            }catch (Exception e){

            }
        }
    }
}
