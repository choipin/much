package com.much.it.test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @description:
 * @project: much
 * @date: 2020/5/3
 * @author: Wenxin
 * @version: 1.0
 */
public class TCPHandler implements Runnable {
    private SelectionKey selectionKey;
    private SocketChannel socketChannel;

    public TCPHandler(SelectionKey selectionKey, SocketChannel socketChannel) {
        this.selectionKey = selectionKey;
        this.socketChannel = socketChannel;
    }
    
    @Override
    public void run() {
        try {
            read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read() throws IOException {
        byte[] buffer = new byte[1024];
        ByteBuffer buf = ByteBuffer.wrap(buffer);

        int read = socketChannel.read(buf);
        if (read ==-1){
            closeChannle();
            return;
        }
        String str = new String(buffer);
        process(str);
        System.out.println(socketChannel.socket().getRemoteSocketAddress().toString() + ">>>" + str);
        send(str);
        selectionKey.selector().wakeup();
    }

    private void send(String str) throws IOException {
        String returnString = "respionse."+socketChannel.socket().getLocalAddress().toString();
        ByteBuffer buf = ByteBuffer.wrap(returnString.getBytes());
        while (buf.hasRemaining()){
            socketChannel.write(buf);
        }
        selectionKey.selector().wakeup();
    }

    private void process(String str) {
    }

    private void closeChannle(){
        try {
            selectionKey.cancel();
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
