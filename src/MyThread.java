import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MyThread {

    private static Map<Integer, Long> saveMap = new HashMap<>();

    Socket socket;

    public MyThread(Socket socket) {
        this.socket = socket;
    }

    public long FibonacciNumber(int number) {
        long answer;

        if (saveMap.isEmpty()) {
            for (int i = 0; i <= number; i++) {
                if (i == 0 || i == 1) {
                    saveMap.put(i, (long) i);
                } else {
                    saveMap.put(i, (saveMap.get(i - 2) + saveMap.get(i - 1)));
                }
            }
            answer = saveMap.get(number - 1);
        } else if (saveMap.get(number) == null) {
            for (int i = saveMap.size(); i <= number + 1; i++) {
                saveMap.put(i, (saveMap.get(i - 2) + saveMap.get(i - 1)));
            }
            answer = saveMap.get(number - 1);
        } else {
            answer = saveMap.get(number - 1);
        }
        return answer;
    }
}
