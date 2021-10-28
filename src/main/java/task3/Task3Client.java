package task3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Task3Client {

    private int port;
    private String host;
    private Object monitor = new Object();

    public Task3Client(int port, String host) {
        this.port = port;
        this.host = host;
    }


    public void startClient() {
        try {
            Socket socket = new Socket(host, port);

            //InputStream in = socket.getInputStream();
            // OutputStream out = socket.getOutputStream();

            //BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            //PrintWriter writer = new PrintWriter(out);

            Runnable runnableReader = () -> {
                while (!socket.isClosed()) {

                    try {

                        InputStream in = socket.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        String serverMessage = reader.readLine();

                        System.out.println("Server said : " + serverMessage);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            };


            Runnable runnableWriter = () -> {

                while (socket.isClosed()) {

                    try {
                        OutputStream out = socket.getOutputStream();
                        PrintWriter writer = new PrintWriter(out);

                        Scanner scanner = new Scanner(System.in);
                        String message = scanner.nextLine();

                        writer.println(message);
                        writer.flush();
                        System.out.println("sent ot server : "+message);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            };


            new Thread(runnableReader).start();
            new Thread(runnableWriter).start();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
