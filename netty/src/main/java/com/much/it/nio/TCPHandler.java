package com.much.it.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @project: much
 * @date: 2020/5/10
 * @author: Wenxin
 * @version: 1.0
 */
public class TCPHandler implements Runnable{
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
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private synchronized void read() throws IOException {
        byte[] buffer = new byte[1024];
        ByteBuffer buf = ByteBuffer.wrap(buffer);
        int read = socketChannel.read(buf);
        if (read ==-1){
            closeChannel();
            return;
        }
        String string = new String(buffer, StandardCharsets.UTF_8);
        if (!string.trim().equals("")) {
            System.out.println(socketChannel.socket().getRemoteSocketAddress().toString() + ">>>" + string);

            //send message
            ByteBuffer responseBody = ByteBuffer.wrap(("server response message!!!" + socketChannel.socket().getRemoteSocketAddress().toString()).getBytes());
            while (responseBody.hasRemaining()){
                socketChannel.write(responseBody);
            }
            selectionKey.selector().wakeup();
        }
    }

    private void closeChannel(){
        try {
            selectionKey.cancel();
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
