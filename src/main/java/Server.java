import handler.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Sends and receives all Http.
 */
public class Server {

    /**
     * Maximum number of connections at one time.
     */
    private static final int MAX_WAITING_CONNECTIONS = 12;

    /**
     * Main server interface.
     */
    private HttpServer server;

    /**
     * Sets up the server with contexts (API calls).
     * @param portNumber the port to set up the server on.
     */
    private void run(String portNumber) {
        System.out.println("Initializing HTTP Server");

        try {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS
            );
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null);

        System.out.println("Creating contexts");

        server.createContext("/user/register", new RegisterHandler());
        server.createContext("/user/login", new LoginHandler());
        server.createContext("/fill", new FillHandler());
        server.createContext("/person/", new FindPersonHandler());
        server.createContext("/person", new GetFamilyHandler());
        server.createContext("/event/", new FindEventHandler());
        server.createContext("/event", new GetFamilyEventsHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/load", new LoadHandler());

        server.createContext("/", new FileHandler());

        System.out.println("Starting server");

        server.start();

        System.out.println("Server started");

    }

    public static void main(String[] args) {
        String portNumber = args[0];
        new Server().run(portNumber);
    }
}
