package com.much.it.netty3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @description:
 * @project: much
 * @date: 2020/5/5
 * @author: Wenxin
 * @version: 1.0
 */
public class NioModel {
    private static int cores = Runtime.getRuntime().availableProcessors();
    static WorkThread[] workThreads = new WorkThread[cores];
    public static void main(String[] args) {
        BossWork bossWork = new BossWork(8090);
        bossWork.run();
        IntStream.range(0,cores).boxed().forEach(integer -> new WorkThread().run());
    }

    static class BossWork implements Runnable{
        private ServerSocketChannel serverSocketChannel;
        private Selector selector;

        BossWork(int port) {
            try {
                this.serverSocketChannel = ServerSocketChannel.open();
                this.selector = Selector.open();
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.bind(new InetSocketAddress(port));
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    while (selector.select() == 0) {
                        continue;
                    }
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    workThreads[new Random().nextInt(cores)].processer(socketChannel);
                }
            }catch (Exception exception){

            }
        }
    }

    static class WorkThread implements Runnable{
        private Selector selector;

        WorkThread() {
            try {
                this.selector = Selector.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void processer(SocketChannel socketChannel){
            try {
                socketChannel.register(selector,SelectionKey.OP_READ);
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
               while (true){
                   while (selector.select()==0){
                       continue;
                   }
                   Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                   while (iterator.hasNext()) {
                       if (iterator.next().isReadable()) {
                           SelectableChannel channel = iterator.next().channel();
                           SocketChannel socketChannel = (SocketChannel) channel;
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
