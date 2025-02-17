package ru.nikolai;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    static final int SIZE = 64;
    static final int PORT = 9999;
    final ExecutorService threadPool = Executors.newFixedThreadPool(SIZE);
    public ConcurrentHashMap<String, ConcurrentHashMap<String, Handler>> handlers = new ConcurrentHashMap<>();

    public void start() {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                //Socket socket = serverSocket.accept();
                final var socket = serverSocket.accept();
                RequestRunnable req = new RequestRunnable(socket);
                threadPool.submit(req);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addHandler(String get, String s, Handler handler) {
        if (!handlers.containsKey(get)) {
            handlers.put(get, new ConcurrentHashMap<>());
        }
        handlers.get(get).put(s, handler);
    }

    public void listen(int i) {
    }
}