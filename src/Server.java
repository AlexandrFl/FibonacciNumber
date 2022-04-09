import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {

    public static void main(String[] args) throws IOException {

        int port = 9090;
        ServerSocket serverSocket = new ServerSocket(port);
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        while (true) {
            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                MyThread myThread = new MyThread(socket);

                while (true) {
                    int number = Integer.parseInt(in.readLine());
                    System.out.println(number);
                    if (number == 0) {
                        out.println("Соединение разорвано");
                        break;
                    } else {
                        Future<Long> future = executorService.submit(() -> myThread.FibonacciNumber(number));
                        out.println("Ответ - " + future.get());
                    }
                }
            } catch (IOException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
