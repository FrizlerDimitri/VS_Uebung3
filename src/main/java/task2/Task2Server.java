package task2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Task2Server {

    private final String indexPath = "src/main/resources/index.html";
    private int port;

    public Task2Server(int port) {
        this.port = port;
    }


    public void startServer() {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port : " + port);

            while (true)
            {
                Runnable run = new Task2ServerRunableWrapper(serverSocket.accept());
                executorService.execute(run);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
