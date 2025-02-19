package ru.nikolai;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        final var server = new Server();
        // код инициализации сервера (из вашего предыдущего ДЗ)

        // добавление хендлеров (обработчиков)
        server.addHandler("GET", "/messages", new Handler() {
            public void handle(Request request, BufferedOutputStream responseStream) {
                var message = "Hello! GET!";
                try {
                    responseStream.write((
                            "HTTP/1.1 200 OK\r\n" +
                                    "Content-Type: " + "text/plain" + "\r\n" +
                                    "Content-Length: " + message.length() + "\r\n" +
                                    "Connection: close\r\n" +
                                    "\r\n"
                    ).getBytes());
                    responseStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        server.addHandler("GET", "/spring.svg", new Handler() {
            public void handle(Request request, BufferedOutputStream responseStream) throws IOException {
                String path = request.getPath();
                final var filePath = Path.of(".", "/public", path);
                final var mimeType = Files.probeContentType(filePath);
                final var length = Files.size(filePath);
                responseStream.write((
                        "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: " + mimeType + "\r\n" +
                                "Content-Length: " + length + "\r\n" +
                                "Connection: close\r\n" +
                                "\r\n"
                ).getBytes());
                Files.copy(filePath, responseStream);
                responseStream.flush();
            }
        });

        server.addHandler("GET", "/spring.png", new Handler() {
            public void handle(Request request, BufferedOutputStream responseStream) throws IOException {
                String path = request.getPath();
                final var filePath = Path.of(".", "/public", path);
                final var mimeType = Files.probeContentType(filePath);
                final var length = Files.size(filePath);
                responseStream.write((
                        "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: " + mimeType + "\r\n" +
                                "Content-Length: " + length + "\r\n" +
                                "Connection: close\r\n" +
                                "\r\n"
                ).getBytes());
                Files.copy(filePath, responseStream);
                responseStream.flush();
            }
        });

        server.addHandler("POST", "/messages", new Handler() {
            public void handle(Request request, BufferedOutputStream responseStream) {
                var message = "Hello! POST!";
                try {
                    responseStream.write((
                            "HTTP/1.1 200 OK\r\n" +
                                    "Content-Type: " + "text/plain" + "\r\n" +
                                    "Content-Length: " + message.length() + "\r\n" +
                                    "Connection: close\r\n" +
                                    "\r\n"
                    ).getBytes());
                    responseStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        server.start();
    }

    private void handle() {
        new Handler() {
            public void handle(Request request, BufferedOutputStream responseStream) throws IOException {
                String path = request.getPath();
                final var filePath = Path.of(".", "/public", path);
                final var mimeType = Files.probeContentType(filePath);
                final var length = Files.size(filePath);
                responseStream.write((
                        "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: " + mimeType + "\r\n" +
                                "Content-Length: " + length + "\r\n" +
                                "Connection: close\r\n" +
                                "\r\n"
                ).getBytes());
                Files.copy(filePath, responseStream);
                responseStream.flush();
            }
        };
    }
}
