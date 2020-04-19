import java.io.*;
import java.net.*;

public class ServerWrite {
    public static void main(String[] args) {
        int serverPort = 8080;
        ServerSocket serverSocket = null;
        ObjectOutputStream toClient = null;
        ObjectInputStream fromClient = null;
        try {
            serverSocket = new ServerSocket(serverPort);
            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("Just connected to " +
                        socket.getRemoteSocketAddress());
                toClient = new ObjectOutputStream(
                        new BufferedOutputStream(socket.getOutputStream()));
                fromClient = new ObjectInputStream(
                        new BufferedInputStream(socket.getInputStream()));
               byte b = fromClient.readByte();
                System.out.println(b);
                toClient.writeObject(new Message(1));
                toClient.flush();
            }
        }
        catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}