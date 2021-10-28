package task2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Task2ServerRunableWrapper implements Runnable {

    private final String indexPath = "src/main/resources/index.html";
    private Socket socket;

    public Task2ServerRunableWrapper(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
            try {
                System.out.println("Connected to client");
                OutputStream out = socket.getOutputStream();

                List<String> list = Files.readAllLines(Paths.get(indexPath));

                out.write("HTTP/1.1 200 OK\r\n".getBytes());
                out.write("Content-Type: text/html\r\n".getBytes());
                //out.write("Content-Length:" + <<here lenght pls>> +"\r\n");
                out.write("\r\n".getBytes());

                for (String line : list) {
                    out.write((line).getBytes());
                }

                out.flush();
                out.close();

            } catch (IOException e) {

                e.printStackTrace();
            }




    }
}
