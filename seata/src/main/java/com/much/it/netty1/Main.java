package com.much.it.netty1;

import java.io.IOException;

/**
 * @description:
 * @project: much
 * @date: 2020/5/4
 * @author: Wenxin
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        try {
            TCPReactor tcpReactor = new TCPReactor(1333);
            tcpReactor.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
