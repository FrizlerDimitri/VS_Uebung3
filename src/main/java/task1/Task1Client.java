package task1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Task1Client {

   private int port;
   private String hostname;

    public Task1Client(int port, String hostname) {
        this.port = port;
        this.hostname = hostname;
    }

    public void startClient()
    {
        Runnable run = () -> {
            System.out.println("Client Started");
            try {
                Socket s = new Socket("localhost", port); //ip Laptop : 192.168.178.38
                                                                    //ip PC : 192.168.178.44
                InputStream in = s.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                OutputStream out = s.getOutputStream();
                PrintWriter writer = new PrintWriter(out);

                writer.println(hostname);
                writer.flush();


                Scanner sc= new Scanner(System.in);
                System.out.println("Please enter a message: ");
                String message = sc.nextLine();

                writer.println(message);
                writer.flush();


                //String eingang = reader.readLine();

                //writer.println("Ich habe empfangern : " + eingang);
                //writer.flush();


            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        new Thread(run).start();






    }






}
