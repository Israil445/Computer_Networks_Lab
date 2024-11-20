import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Base64;

public class mail {
    public static DataOutputStream dos;//used for writing to the socket
    public static BufferedReader br;//used for reading from the socket

    public static void send(String s) throws Exception{
        dos.writeBytes(s);
        Thread.sleep(1000);
        System.out.println("Client: "+s);
    }

    public static void main(String[] args) throws Exception {
        String user = "s2010876110@ru.ac.bd";
        String pass = "israil_bkash_01304178344";

        //convert the user name and password into base-64
        String username = new String(Base64.getEncoder().encode(user.getBytes()));
        String password = new String(Base64.getEncoder().encode(pass.getBytes()));

        //create ssl socket
        SSLSocket s = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com",465);
        dos = new DataOutputStream(s.getOutputStream());
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        send("EHLO smtp.gmail.com\r\n");//establish connection to the smtp server

        System.out.println("SERVER: "+ br.readLine());
        System.out.println("SERVER: "+ br.readLine());
        System.out.println("SERVER: "+ br.readLine());
        System.out.println("SERVER: "+ br.readLine());
        System.out.println("SERVER: "+ br.readLine());
        System.out.println("SERVER: "+ br.readLine());
        System.out.println("SERVER: "+ br.readLine());
        System.out.println("SERVER: "+ br.readLine());
        System.out.println("SERVER: "+ br.readLine());

        send("AUTH LOGIN\r\n");//SMTP authentication process.
            System.out.println("SERVER: "+br.readLine());

        send(username+"\r\n");
            System.out.println("SERVER: "+br.readLine());

        send(password+"\r\n");
        System.out.println("SERVER: "+br.readLine());

        send("MAIL FROM:<s2010876110@ru.ac.bd>\r\n");
        System.out.println("SERVER: "+br.readLine());

        send("RCPT TO:<esrailman445@gmail.com>\r\n");
        System.out.println("SERVER: "+br.readLine());

        send("DATA\r\n");//mail body start here
        System.out.println("SERVER: "+br.readLine());

        send("FROM: 2010876110@ru.ac.bd\r\n");
        send("TO: esrailman445@gmail.com\r\n");
        send("Subject: Email Test"+ LocalDateTime.now()+"\r\n");
        send("THIS IS A TEST MAIL\r\n");
        send(".\r\n");//ending the mail body part

        System.out.println("SERVER: "+br.readLine());

        send("QUIT\r\n");
        System.out.println("SERVER: "+br.readLine());

    }


}
