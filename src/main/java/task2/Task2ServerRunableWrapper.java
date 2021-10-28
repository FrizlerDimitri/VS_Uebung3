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

                //InputStream in= socket.getInputStream();
                OutputStream out = socket.getOutputStream();

                //BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                //PrintWriter writer = new PrintWriter(out);

                List<String> list = Files.readAllLines(Paths.get(indexPath));

                out.write("HTTP/1.1 200 OK\r\n".getBytes());
                out.write("Content-Type: text/html\r\n".getBytes());
                //out.write("Content-Length:" + <<here lenght pls>> +"\r\n");
                out.write("\r\n".getBytes());

                for (String line : list) {
                    out.write((line).getBytes());
                    //writer.println(line);
                }

                out.flush();
                //in.close();
                out.close();


            } catch (IOException e) {

                e.printStackTrace();
            }




    }
}
