

import sun.applet.Main;

import javax.swing.*;
import java.net.*;
import java.io.*;

import static java.lang.System.out;

public class Server {
    public void run() {
        try {
            /**
             * Сервер начинается с создания ServerSocket:
             */
            int serverPort = 4021;
            ServerSocket serverSocket = new ServerSocket(serverPort);
            //serverSocket.setSoTimeout(10000;
            while (true) {
                out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
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
                out.println("Just connected to " + server.getRemoteSocketAddress());

                PrintWriter toClient =
                        new PrintWriter(server.getOutputStream(), true);
                BufferedReader fromClient =
                        new BufferedReader(
                                new InputStreamReader(server.getInputStream()));
                String line = fromClient.readLine();
                out.println("Server received: " + line);
               // toClient.println(content);
                String content = "";
                StringBuilder contentBuilder = new StringBuilder();
                try {
                    BufferedReader in = new BufferedReader(new FileReader(line));
                    String str;
                    while ((str = in.readLine()) != null) {
                        contentBuilder.append(str);
                    }
                    in.close();
                } catch (IOException e) {
                }
                content = contentBuilder.toString();
                toClient.println(content);  // send to server

            }
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }


    }


}