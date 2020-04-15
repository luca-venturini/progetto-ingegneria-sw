package it.polimi.ingsw.ParenteVenturini.Network.Server;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private ExecutorService pool;

    public Server(int p){
        port = p;
        pool = Executors.newCachedThreadPool();
    }

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
                    }
                });
            }catch(Exception e){
                break;
            }
        }
    }

    public static void main(String[] args) {
        Server s = new Server(1337);
        s.startServer();
    }
}