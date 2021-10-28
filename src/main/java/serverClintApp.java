import task1.Task1Client;
import task1.Task1Server;

public class serverClintApp {
    public static void main(String[] args) {

        int port = 8070;

        String role = "client";

        //if (role.toLowerCase().equals("server")) {
            startSever(port);
        //} else if (role.toLowerCase().equals("client")) {
            startClient(port);
        //}

    }





    private static void startSever(int port) {

        Task1Server server=Task1Server.getServerInstance(port);
        server.startServer();

    }


    private static void startClient(int port) {

        Task1Client client = new Task1Client(port, "TestClient");
        client.startClient();

    }
}
