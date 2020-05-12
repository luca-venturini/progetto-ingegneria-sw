package it.polimi.ingsw.ParenteVenturini.Network.Server;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * server class, handle teh connection of new clients
 */
public class Server {
    /** the port */
    private int port;
    /** used to create threads */
    private ExecutorService pool;

    /**
     * init the server
     * @param p the port
     */
    public Server(int p){
        port = p;
        pool = Executors.newCachedThreadPool();
    }

    /**
     * start the server, creating a new thread for each client connected
     */
    public void startServer(){
        ServerSocket serverSocket = null;
        ArrayList<Thread> threads = new ArrayList<>();
        try{
            serverSocket = new ServerSocket(port);
        }
        catch(Exception e){
        }
        System.out.println("Server in esecuzione");
        while(true){
            try{
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                pool.submit(() -> {
                    try {
                        ClientThreadFromServer clientHandler = new ClientThreadFromServer(socket);
                        clientHandler.handleConnection();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                });
            }catch(Exception e){
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * start the server
     * @param args args, no one required
     */
    public static void main(String[] args) {
        Server s = new Server(1337);
        s.startServer();
    }
}