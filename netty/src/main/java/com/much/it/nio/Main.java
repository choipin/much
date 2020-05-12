package com.much.it.nio;

/**
 * @description:
 * @project: much
 * @date: 2020/5/10
 * @author: Wenxin
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        TCPReator tcpReator = new TCPReator(1333);
        tcpReator.run();
    }
}
