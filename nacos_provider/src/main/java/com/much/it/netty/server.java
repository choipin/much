package com.much.it.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @project: much
 * @date: 2020/5/3
 * @author: Wenxin
 * @version: 1.0
 */
public class server {
    private static ExecutorService workPool = Executors.newCachedThreadPool();

    abstract class ReactorThread extends Thread {
        Selector selector;
        volatile Boolean running = false;
        LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<Runnable>();

        public abstract void handler(SelectableChannel selectableChannel) throws IOException, ExecutionException, InterruptedException;

        private ReactorThread() throws IOException {
            selector = Selector.open();
        }

        @Override
        public void run() {
            while (running) {
                try {
                    Runnable task;
                    while ((task = taskQueue.poll()) != null) {
                        task.run();
                    }
                    selector.select(1000);
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey next = iterator.next();
                        iterator.remove();
                        int readyOps = next.readyOps();
                        if ((readyOps & (SelectionKey.OP_READ | SelectionKey.OP_ACCEPT)) != 0 || readyOps == 0) {
                            try {
                                SelectableChannel channel = (SelectableChannel) next.attachment();
                                channel.configureBlocking(false);
                                handler(channel);
                                if (channel.isOpen()) {
                                    next.cancel();
                                }
                            } catch (Exception e) {
                                next.cancel();
                            }

                        }
                    }
                    selector.selectNow();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        private SelectionKey register(SelectableChannel selectableChannel) throws ExecutionException, InterruptedException {
            FutureTask<SelectionKey> futureTask = new FutureTask<>(() -> selectableChannel.register(selector, 0, selectableChannel));
            taskQueue.add(futureTask);
            return futureTask.get();
        }

        private void doStart() {
            if (!running) {
                running = true;
                start();
            }
        }
    }

    private ServerSocketChannel serverSocketChannel;
    private ReactorThread[] mainReactorThreads = new ReactorThread[1];
    private ReactorThread[] subReactorThreads = new ReactorThread[8];

    private void initGroup() throws IOException {
        for (int i = 0; i < subReactorThreads.length; i++) {
            subReactorThreads[i] = new ReactorThread() {
                @Override
                public void handler(SelectableChannel selectableChannel) throws IOException {
                    SocketChannel socketChannel = (SocketChannel) selectableChannel;
                    ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                    while (socketChannel.isOpen()) {
                        if (requestBuffer.position() > 0) {
                            break;
                        }
                    }
                    if (requestBuffer.position() == 0) {
                        return;
                    }
                    requestBuffer.flip();
                    byte[] content = new byte[requestBuffer.limit()];
                    requestBuffer.get(content);
                    System.out.println(new String(content));
                    System.out.println(Thread.currentThread().getName() + " receid message" + ((SocketChannel) selectableChannel).getRemoteAddress());

                    workPool.submit(() -> {
                    });
                    String response = "HTTP/1.1 200 OK\r\n Content-Lenght: 11\r\n\r\n Hello World";
                    ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                    while (buffer.hasRemaining()) {
                        ((SocketChannel) selectableChannel).write(buffer);
                    }

                }
            };
        }

        for (int i = 0; i < mainReactorThreads.length; i++) {
            mainReactorThreads[i] = new ReactorThread() {
                AtomicInteger incr = new AtomicInteger(0);

                @Override
                public void handler(SelectableChannel selectableChannel) throws IOException, ExecutionException, InterruptedException {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectableChannel;
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    int index = incr.getAndIncrement() % subReactorThreads.length;
                    ReactorThread workEventLoop = subReactorThreads[index];
                    workEventLoop.doStart();
                    SelectionKey selectionKey = workEventLoop.register(socketChannel);
                    selectionKey.interestOps(SelectionKey.OP_READ);
                    System.out.println(Thread.currentThread().getName().toString() + "==>has recieve a connection==> " + socketChannel.getRemoteAddress());

                }
            };
        }
    }

    private void initAndRegister() throws IOException, ExecutionException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        int index = new Random().nextInt(mainReactorThreads.length);
        mainReactorThreads[index].doStart();
        SelectionKey selectionKey = mainReactorThreads[index].register(serverSocketChannel);
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);
    }

    private void bind() throws IOException {
        serverSocketChannel.bind(new InetSocketAddress(8080));
        System.out.println("binding finish 8080");
    }

    public static void main(String[] args) throws IOException {
        server server = new server();
        server.initGroup();
        server.bind();
    }
}
