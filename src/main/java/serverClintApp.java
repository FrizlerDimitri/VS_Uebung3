import task1.Task1Client;
import task1.Task1Server;
import task2.Task2Server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class serverClintApp {
    public static void main(String[] args) {

        int port = 8061;
        String role = "client";

        //-------------Task 1

        //if (role.toLowerCase().equals("server")) {
            //startSever1(port);
        //} else if (role.toLowerCase().equals("client")) {
           // startClient1(port);
        //}

        //-------------Task 2

       // System.out.println(Paths.get("src/main/resources/index.html").toAbsolutePath());

        startServer2(port);



    }




    private static  void startServer2(int port)
    {
        Task2Server server = new Task2Server(port);
        server.startServer();

    }


    private static void startSever1(int port) {

        Task1Server server=Task1Server.getServerInstance(port);
        server.startServer();

    }


    private static void startClient1(int port) {

        Task1Client client = new Task1Client(port, "TestClient");
        client.startClient();

    }
}
