

import java.net.*;
import java.io.*;

public class Server {
    public void run() {
        try {
            /**
             * Сервер начинается с создания ServerSocket:
             */
            int serverPort = 4021;
            ServerSocket serverSocket = new ServerSocket(serverPort);
            serverSocket.setSoTimeout(10000);
            while (true) {
                System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
                /**
                 * После создания ServerSocket, сервер вызывает метод Accept() в ServerSocket
                 * для прослушивания входящих запросов на соединение от клиентов.
                 */
                Socket server = serverSocket.accept();
                /**
                 * Метода Accept() ждет, пока клиент запрашивает соединение;
                 * затем он возвращает сокет, по которому подключается клиент к серверу.
                 * Затем сервер получает входной и выходной потоки из сокета и использует их для общения с клиентом.
                 *Когда взаимодействие заканчивается, клиент, сервер, или оба, закрывают соединение, и сервер ожидает
                 *запроса на соединение от другого клиента.
                 */
                System.out.println("Just connected to " + server.getRemoteSocketAddress());

                PrintWriter toClient =
                        new PrintWriter(server.getOutputStream(), true);
                BufferedReader fromClient =
                        new BufferedReader(
                                new InputStreamReader(server.getInputStream()));
                String line = fromClient.readLine();
                System.out.println("Server received: " + line);
                toClient.println("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");
            }
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}