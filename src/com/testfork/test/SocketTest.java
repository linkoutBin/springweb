package com.testfork.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author: xingshulin Date: 2019/2/28 下午5:10
 *
 *
 * Description: TODO Version: 1.0
 **/
public class SocketTest {

  private static volatile boolean quit = true;

  public static void main(String[] args) {
    Socket socket = null;
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket();
      InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 1099);
      serverSocket.bind(inetSocketAddress);
      while (quit) {
        socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
        byte[] content = new byte[1024];
        bufferedInputStream.read(content, 0, content.length);
        System.out.println(new String(content).trim());
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (null != socket) {
          socket.close();
        }
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
      try {
        if (null != serverSocket) {
          serverSocket.close();
        }
      } catch (IOException io) {
        io.printStackTrace();
      }
    }
  }

}
