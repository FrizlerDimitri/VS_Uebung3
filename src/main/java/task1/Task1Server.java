package task1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Task1Server {

    private int port;

    private static Task1Server server = null;


    private Task1Server(int port) {
        this.port = port;
    }

    public static Task1Server getServerInstance(int port) {
        if (server == null) {
            server = new Task1Server(port);
            Task1Server server = Task1Server.server;
            return server;
        } else {
            return server;
        }
    }

    public void startServer() {
        Runnable run = () -> {
            System.out.println("Server started ...");
            try (ServerSocket serverSocket = new ServerSocket(this.port)) {
                while (true) {

                    try {
                        Socket s = serverSocket.accept();
                        System.out.println("Verbindung aufgebaut");


                        InputStream in = s.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        OutputStream out = s.getOutputStream();
                        PrintWriter writer = new PrintWriter(out);

                        //writer.println("Hallo");
                        //writer.flush();

                        String clientName = reader.readLine();
                        System.out.println("Client : connected to " + clientName + " on port " + port);

                        String message = reader.readLine();

                        System.out.println(clientName + " : " + message);


                        s.close();
                        System.out.println("connection closed");
                        //String antwort = reader.readLine();
                        //System.out.println("Antwort des Clients : " + antwort);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        };

        new Thread(run).start();

    }
}
