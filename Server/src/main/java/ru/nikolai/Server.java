package ru.nikolai;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    static final int SIZE = 64;
    static final int PORT = 9999;

    public static void main(String[] args) throws IOException {

        final ExecutorService threadPool = Executors.newFixedThreadPool(SIZE);

        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true){
            //Socket socket = serverSocket.accept();
            final var socket = serverSocket.accept();
            RequestRunnable req = new RequestRunnable(socket);
            threadPool.submit(req);

        }
    }
}