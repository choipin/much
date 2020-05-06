package com.much.it.test;

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
public class NIOServer {
    private static int cores = Runtime.getRuntime().availableProcessors();
    static WorkThread[] workThread = new WorkThread[cores];
    public static void main(String[] args) {
        BossThread bossThread = new BossThread(8088);
        bossThread.run();
        IntStream.range(0,cores).boxed().forEach(integer -> new WorkThread().run());
    }

    static class BossThread implements Runnable{
        ServerSocketChannel serverSocketChannel;
        Selector selector;
        public BossThread(int port) {
            try {
                serverSocketChannel = ServerSocketChannel.open();
                selector = Selector.open();
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.bind(new InetSocketAddress(port));
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("BossThread is starting");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            try {
                while (true){
                    if (selector.select()==0) {
                        continue;
                    }
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        workThread[new Random().nextInt(cores)].processSocket(socketChannel);
                    }
                    selector.close();
                    selector.selectedKeys().clear();
                }
            }catch (Exception e){

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


        public void processSocket(SocketChannel socketChannel){
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
                    if (selector.select() == 0) {
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
