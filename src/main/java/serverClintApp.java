import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class serverClintApp {
    public static void main(String[] args) {

        int port = 8070;

        String role = "client";

        if (role.toLowerCase().equals("server")) {
            startSever(port);
        } else if (role.toLowerCase().equals("client")) {
            startClient(port);
        }

    }

    private static void startSever(int port) {

        Runnable run = () -> {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                while (true) {

                    try {
                        Socket s = serverSocket.accept();
                        System.out.println("Verbindung aufgebaut");


                        InputStream in = s.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        OutputStream out = s.getOutputStream();
                        PrintWriter writer = new PrintWriter(out);

                        writer.println("Hallo");
                        writer.flush();

                        String antwort = reader.readLine();
                        System.out.println("Antwort des Clients : " + antwort);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        };

        new Thread(run).start();
        System.out.println("Server started ...");


    }


    private static void startClient(int port) {

        Runnable run = () -> {

            try {
                Socket s = new Socket("192.168.178.38", port); //ip Laptop : 192.168.178.38
                                                                        //ip PC : 192.168.178.44
                InputStream in = s.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                OutputStream out = s.getOutputStream();
                PrintWriter writer = new PrintWriter(out);

                String eingang = reader.readLine();

                writer.println("Ich habe empfangern : " + eingang);
                writer.flush();


            } catch (IOException e) {
                e.printStackTrace();
            }


        };

        new Thread(run).start();
        System.out.println("Client Started");


    }
}
