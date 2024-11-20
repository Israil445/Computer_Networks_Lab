import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(22222);
        System.out.println("Server Started..");

        while (true) {

            try {
                Socket socket = ss.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                //read from client...
                Object cMsg = ois.readObject();
                System.out.println("From Client: " + (String) cMsg);

                //send to client...
                Scanner sc = new Scanner(System.in);
                String messege = sc.nextLine();
                oos.writeObject(messege);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}