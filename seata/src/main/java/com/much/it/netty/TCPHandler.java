package com.much.it.netty;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @description:
 * @project: much
 * @date: 2020/5/4
 * @author: Wenxin
 * @version: 1.0
 */
public class TCPHandler implements Runnable {

    private final SelectionKey selectionKey;
    private final SocketChannel socketChannel;
    int status;

    public TCPHandler(SelectionKey selectionKey, SocketChannel socketChannel) {
        this.selectionKey = selectionKey;
        this.socketChannel = socketChannel;
        status = 0;
    }

    @Override
    public void run() {
        try {
            if (status == 0) {
                read();
            } else {
                send();
            }
        } catch (Exception e) {
            try {
                selectionKey.cancel();
                socketChannel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }

    private void send() throws IOException {
        String str = "Your message from message queue" + socketChannel.socket().getRemoteSocketAddress().toString();
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        while (buffer.hasRemaining()){
            socketChannel.write(buffer);
        }
        status = 0;
        // 通過key改變通道註冊的事件
        selectionKey.interestOps(SelectionKey.OP_READ);
        // 使一個阻塞住的selector操作立即返回
        selectionKey.selector().wakeup();

    }

    private void read() throws IOException {
        byte[] buffer = new byte[1024];
        ByteBuffer buf = ByteBuffer.wrap(buffer);
        int numByte = socketChannel.read(buf);
        if (numByte ==-1){
            System.out.println("[warining A client has been closed]");
            //todo
            return;
        }
        String str = new String(buffer);
        if (StringUtils.isAllBlank(str)){
            //todo
            System.out.println(socketChannel.socket().getRemoteSocketAddress().toString() + ">>>" + str);

            status = 1;
            selectionKey.interestOps(SelectionKey.OP_WRITE);
            selectionKey.selector().wakeup();
        }
    }

    public static void main(String[] args) throws IOException {
        TCPReactor tcpReactor = new TCPReactor(1333);
        tcpReactor.run();
    }

}
