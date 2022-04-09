import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
	String host = "127.0.0.1";
    int port = 9090;

        Socket socket = new Socket(host, port);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            String  x;

            while (true) {
                System.out.println("Введите число больше или равное 2\nВведите 0 для завершения работы");
                x = scanner.nextLine();
                out.println(x);
                if (x.equals("0")) {
                    System.out.println(in.readLine());
                    break;
                }
                System.out.println("Server: " + in.readLine());
            }
        }
    }
}
